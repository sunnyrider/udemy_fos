package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

public class PaymentCancelledEvent extends PaymentEvent {
	private final DomainEventPublisher<PaymentCancelledEvent> domainEventPublisherPaymentCancelledEvent;

	public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt, 
			DomainEventPublisher<PaymentCancelledEvent> eventPublisherPaymentCancelledEvent) {
		super(payment, createdAt, Collections.emptyList());
		domainEventPublisherPaymentCancelledEvent = eventPublisherPaymentCancelledEvent;
	}

	@Override
	public void fire() {
		domainEventPublisherPaymentCancelledEvent.publish(this);
	}
}
