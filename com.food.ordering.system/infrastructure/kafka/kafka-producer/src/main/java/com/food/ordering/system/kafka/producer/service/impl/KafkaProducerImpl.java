package com.food.ordering.system.kafka.producer.service.impl;

import java.io.Serializable;

import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;

import jakarta.annotation.PreDestroy;

@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> 
		implements KafkaProducer<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerImpl.class);

	private final KafkaTemplate<K, V> kafkaTemplate;

	public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
    	LOGGER.info("Sending message={} to topic={}", message, topicName);
        try {
            ListenableFuture<SendResult<K, V>> kafkaResultFuture 
            	= (ListenableFuture<SendResult<K, V>>) kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.addCallback(callback);
        } catch (KafkaException e) {
        	LOGGER.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
        	LOGGER.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

}
