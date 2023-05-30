package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
	private static final long serialVersionUID = -201104703263879506L;

	public PaymentDomainException(String message) {
		super(message);
	}

	public PaymentDomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
