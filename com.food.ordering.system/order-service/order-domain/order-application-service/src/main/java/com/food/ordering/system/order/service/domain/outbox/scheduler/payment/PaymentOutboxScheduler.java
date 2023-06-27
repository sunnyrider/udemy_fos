package com.food.ordering.system.order.service.domain.outbox.scheduler.payment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.outbox.model.payload.OrderPaymentOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import com.food.ordering.system.outbox.OutboxScheduler;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;

@Component
public class PaymentOutboxScheduler implements OutboxScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentOutboxScheduler.class);

	private final PaymentOutboxHelper paymentOutboxHelper;
	private final PaymentRequestMessagePublisher paymentRequestMessagePublisher;

	public PaymentOutboxScheduler(PaymentOutboxHelper paymentOutboxHelper,
			PaymentRequestMessagePublisher paymentRequestMessagePublisher) {
		this.paymentOutboxHelper = paymentOutboxHelper;
		this.paymentRequestMessagePublisher = paymentRequestMessagePublisher;
	}

	@Override
	@Transactional
	@Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}",
					initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
	public void processOutboxMessage() {
		Optional<List<OrderPaymentOutboxMessage>> outboxMessageResponse =
				paymentOutboxHelper.getPaymentOutboxMessageByOutboxStatusAnsSagaStatus(
						OutboxStatus.STARTED, 
						SagaStatus.STARTED,
						SagaStatus.COMPENSATING);

		if (outboxMessageResponse.isPresent() && outboxMessageResponse.get().size() > 0) {
			LOGGER.info("Recived {} OrderPaymentOutboxMessage with ids : {}. Sending to message bus.",
					outboxMessageResponse.get().size(), 
					outboxMessageResponse.get().stream().map(message ->
						message.getId().toString()).collect(Collectors.toList())
					);
			outboxMessageResponse.get().forEach(message ->
				paymentRequestMessagePublisher.publish(message, this::updateOutboxStatus));
			LOGGER.info("{} OrderPaymentOutboxMessage sent to message bus!", 
					outboxMessageResponse.get().size());
		}
	}

	private void updateOutboxStatus(OrderPaymentOutboxMessage orderPaymentOutboxMessage, OutboxStatus outboxStatus) {
		orderPaymentOutboxMessage.setOutboxStatus(outboxStatus);
		paymentOutboxHelper.save(orderPaymentOutboxMessage);
		LOGGER.info("OrderPaymentOutboxMessage is updated with outbox status + {}",
				outboxStatus.name());
	}

}
