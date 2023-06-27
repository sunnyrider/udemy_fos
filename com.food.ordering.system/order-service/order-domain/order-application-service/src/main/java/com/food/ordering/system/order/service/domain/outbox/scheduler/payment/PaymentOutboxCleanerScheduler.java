package com.food.ordering.system.order.service.domain.outbox.scheduler.payment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.domain.outbox.model.payload.OrderPaymentOutboxMessage;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;

@Component
public class PaymentOutboxCleanerScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentOutboxCleanerScheduler.class);

	private final PaymentOutboxHelper paymentOutboxHelper;

	public PaymentOutboxCleanerScheduler(PaymentOutboxHelper paymentOutboxHelper) {
		this.paymentOutboxHelper = paymentOutboxHelper;
	}

	public void processOutboxMessage() {
		Optional<List<OrderPaymentOutboxMessage>> outboxMessageResponse =
				paymentOutboxHelper.getPaymentOutboxMessageByOutboxStatusAnsSagaStatus(
						OutboxStatus.COMPLETED, 
						SagaStatus.SUCCEEDED,
						SagaStatus.FAILED,
						SagaStatus.COMPENSATED);

		if (outboxMessageResponse.isPresent()) {
			List<OrderPaymentOutboxMessage> outboxMessage = outboxMessageResponse.get();
			LOGGER.info("Received {} OrderPaymentOutboxMessage(s) for clean-up The payloads : {}",
					outboxMessage.size(),
					outboxMessage.stream().map(msg -> msg.getPayload())
						.collect(Collectors.toList()));

			paymentOutboxHelper.deletePaymentOutboxMessageByOutboxStatusAndSagaStatus(
					OutboxStatus.COMPLETED, 
					SagaStatus.SUCCEEDED,
					SagaStatus.FAILED,
					SagaStatus.COMPENSATED);

			LOGGER.info("OrderPaymentOutboxMessage(s) deleted!", outboxMessage.size());
		}
	}
}
