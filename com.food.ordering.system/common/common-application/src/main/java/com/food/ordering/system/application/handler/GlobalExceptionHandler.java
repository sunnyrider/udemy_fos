package com.food.ordering.system.application.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handleException(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
		return ErrorDTO.builder()
				.Code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.Message("Unexpected error!")
				.build();
	}

	@ResponseBody
	@ExceptionHandler(value = {ValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(ValidationException ex) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDTO errorDto;

		if (ex instanceof ConstraintViolationException) {
			String violations = extractViolationFromException((ConstraintViolationException) ex);
			LOGGER.error(violations, ex);
			errorDto = ErrorDTO.builder()
				.Code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.Message(violations)
				.build();
		} else {
			String expMsg = ex.getMessage();
			LOGGER.error(expMsg, ex);
			errorDto = ErrorDTO.builder()
					.Code(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.Message(expMsg)
					.build();
		}

		return errorDto;
	}

	private String extractViolationFromException(ConstraintViolationException ex) {
		return ex.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("--"));
	}
}
