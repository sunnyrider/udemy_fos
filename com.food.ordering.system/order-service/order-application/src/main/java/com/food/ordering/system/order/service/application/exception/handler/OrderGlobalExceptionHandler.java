package com.food.ordering.system.order.service.application.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;

@ControllerAdvice
public class OrderGlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderGlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = {OrderDomainException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(OrderDomainException ex) {
		LOGGER.error(ex.getMessage(), ex);
		return ErrorDTO.builder()
				.Code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.Message(ex.getMessage())
				.build();
	}

	@ResponseBody
	@ExceptionHandler(value = {OrderNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handleException(OrderNotFoundException ex) {
		LOGGER.error(ex.getMessage(), ex);
		return ErrorDTO.builder()
				.Code(HttpStatus.NOT_FOUND.getReasonPhrase())
				.Message(ex.getMessage())
				.build();
	}

}
