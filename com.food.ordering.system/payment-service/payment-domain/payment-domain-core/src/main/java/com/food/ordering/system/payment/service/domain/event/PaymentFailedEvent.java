package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

public class PaymentFailedEvent extends PaymentEvent {
	private final DomainEventPublisher<PaymentFailedEvent> domainEventPublisherPaymentFailedEvent;

	public PaymentFailedEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages, 
			DomainEventPublisher<PaymentFailedEvent> eventPublisherPaymentFailedEvent) {
		super(payment, createdAt, failureMessages);
		domainEventPublisherPaymentFailedEvent = eventPublisherPaymentFailedEvent;
	}

	@Override
	public void fire() {
		domainEventPublisherPaymentFailedEvent.publish(this);
	}
}
