package com.food.ordering.system.payment.service.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.payment.service.domain.entity.Payment;

public abstract class PaymentEvent implements DomainEvent<Payment> {

	private final Payment payment;
	private final ZonedDateTime createdAt;
	private List<String> failureMessages;

	public PaymentEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages) {
		super();
		this.payment = payment;
		this.createdAt = createdAt;
		this.failureMessages = failureMessages;
	}

	public List<String> getFailureMessages() {
		return failureMessages;
	}

	public void setFailureMessages(List<String> failureMessages) {
		this.failureMessages = failureMessages;
	}

	public Payment getPayment() {
		return payment;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
}
