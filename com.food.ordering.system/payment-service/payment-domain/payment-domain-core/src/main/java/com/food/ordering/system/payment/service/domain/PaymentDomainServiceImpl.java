package com.food.ordering.system.payment.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.ordering.system.domain.DomainConstants;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;

public class PaymentDomainServiceImpl implements PaymentDomainService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDomainServiceImpl.class);

	@Override
	public PaymentEvent validateAndInitializePayment(Payment payment, CreditEntry creditEntry,
			List<CreditHistory> creditHistories, List<String> failureMessages,
			DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
			DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {

		payment.validatePayment(failureMessages);
		payment.initialitePayment();
		validateCreditEntry(payment, creditEntry, failureMessages);
		substractCreditEntry(payment, creditEntry);
		updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
		validateCreditHistory(creditEntry, creditHistories, failureMessages);

		if (failureMessages.isEmpty()) {
			LOGGER.info("Payment initialized for order id : {}", payment.getOrderId().getValue());
			payment.updateStatus(PaymentStatus.COMPLETED);
			return new PaymentCompletedEvent(payment, 
					ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
					paymentCompletedEventDomainEventPublisher);
		} else {
			LOGGER.info("Payment initiation failed for order id : {}", 
					payment.getOrderId().getValue());
			payment.updateStatus(PaymentStatus.FAILED);
			return new PaymentFailedEvent(payment, 
					ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)), 
					failureMessages,
					paymentFailedEventDomainEventPublisher);
		}
	}

	@Override
	public PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry,
			List<CreditHistory> creditHistories, List<String> failureMessages,
			DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
			DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {

		payment.validatePayment(failureMessages);
		addCreditEntry(payment, creditEntry);
		updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

		if (failureMessages.isEmpty()) {
			LOGGER.info("Payment is cancelled for order id : {}", 
					payment.getOrderId());
			payment.updateStatus(PaymentStatus.CANCELLED);
			return new PaymentCancelledEvent(payment, 
					ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
					paymentCancelledEventDomainEventPublisher);
		} else {
			LOGGER.error("Payment cancellation has failed for order id : {}",
					payment.getOrderId());
			payment.updateStatus(PaymentStatus.FAILED);
			return new PaymentFailedEvent(payment, 
					ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)), 
					failureMessages,
					paymentFailedEventDomainEventPublisher);
		}
	}

	private void addCreditEntry(Payment payment, CreditEntry creditEntry) {
		creditEntry.addCreditAmount(payment.getPrice());
	}

	private void substractCreditEntry(Payment payment, CreditEntry creditEntry) {
		creditEntry.substractAmount(payment.getPrice());
	}

	private void validateCreditEntry(Payment payment, CreditEntry creditEntry, List<String> failureMessages) {
		if (payment.getPrice().isGreaterthan(creditEntry.getTotalCreditAmount())) {
			LOGGER.error("Customer {} does not have enough credit for payment",
					payment.getCustomerId().getValue());
			failureMessages.add("Customer " + payment.getCustomerId().getValue() 
					+ "does not have enough credit for payment");
		}
	}

	private void validateCreditHistory(CreditEntry creditEntry, 
			List<CreditHistory> creditHistories,
			List<String> failureMessages) {
		Money totalCreditHistory = paymentAmount(creditHistories, TransactionType.CREDIT);
		Money totalDebitHistory = paymentAmount(creditHistories, TransactionType.DEBIT);

		if (totalDebitHistory.isGreaterthan(totalCreditHistory)) {
			LOGGER.error("Customer with id : {} does not have enough credit "
					+ "according to credit history", creditEntry.getCustomerId().getValue());
			failureMessages.add("Customer with id : " 
					+ creditEntry.getCustomerId().getValue() 
					+ " does not have enough credit "
					+ "according to credit history");
		}

		if (!creditEntry.getTotalCreditAmount().equals(totalCreditHistory.substract(totalDebitHistory))) {
			LOGGER.error("Credit history total is not equal to current credit for customer {}",
					creditEntry.getCustomerId().getValue());
			failureMessages.add("Credit history total is not equal "
					+ "to current credit for customer " + creditEntry.getCustomerId().getValue());
		}
	}

	private Money paymentAmount(List<CreditHistory> creditHistories,
			TransactionType transactionType) {
		return creditHistories.stream()
				.filter(creditHistory -> transactionType == creditHistory.getTransactionType())
				.map(amount -> amount.getAmount())
				.reduce(Money.ZERO, Money::add);
	}

	private void updateCreditHistory(Payment payment, List<CreditHistory> creditHistories, 
			TransactionType transactionType) {
        creditHistories.add(CreditHistory.builder()
                .creditHistoryId(new CreditHistoryId(UUID.randomUUID()))
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .transactionType(transactionType)
                .build());
	}
}
