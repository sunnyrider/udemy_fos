package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.event.TransactionalEventListener;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

public class OrderCreatedEventApplicationListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedEventApplicationListener.class);

	private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

	public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestMessagePublisher publisher) {
		this.orderCreatedPaymentRequestMessagePublisher = publisher;
	}

	@TransactionalEventListener
	void process(OrderCreatedEvent orderCreatedEvent) {
		orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
	}
}
