package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
	private static final long serialVersionUID = 8719175835590604524L;

	public OrderDomainException(String message) {
		super(message);
	}

	public OrderDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
