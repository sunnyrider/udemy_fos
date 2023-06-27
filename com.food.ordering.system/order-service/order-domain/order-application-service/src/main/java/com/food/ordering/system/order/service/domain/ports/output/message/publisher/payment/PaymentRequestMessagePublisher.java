package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import java.util.function.BiConsumer;

import com.food.ordering.system.order.service.domain.outbox.model.payload.OrderPaymentOutboxMessage;
import com.food.ordering.system.outbox.OutboxStatus;

public interface PaymentRequestMessagePublisher {

	void publish(OrderPaymentOutboxMessage paymentOutboxMessage,
			BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
