package com.food.ordering.system.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException {
	private static final long serialVersionUID = 8746264659273779604L;

	public KafkaProducerException(String message) {
		super(message);
	}
}
