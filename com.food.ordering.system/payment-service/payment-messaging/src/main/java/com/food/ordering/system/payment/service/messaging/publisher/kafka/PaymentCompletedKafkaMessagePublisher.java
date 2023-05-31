package com.food.ordering.system.payment.service.messaging.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.payment.service.domain.config.PaymentServiceConfigData;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;

@Component
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCompletedKafkaMessagePublisher.class);

	private final PaymentMessagingDataMapper paymentMessagingDataMapper;
	private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
	private final PaymentServiceConfigData paymentServiceConfigData;
	private final KafkaMessageHelper kafkaMessageHelper;

	public PaymentCompletedKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
			KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
			PaymentServiceConfigData paymentServiceConfigData, KafkaMessageHeaders kafkaMessageHeaders, KafkaMessageHelper kafkaMessageHelper) {
		this.paymentMessagingDataMapper = paymentMessagingDataMapper;
		this.kafkaProducer = kafkaProducer;
		this.paymentServiceConfigData = paymentServiceConfigData;
		this.kafkaMessageHelper = kafkaMessageHelper;
	}

	@Override
	public void publish(PaymentCompletedEvent domainEvent) {
		String orderId = domainEvent.getPayment().getOrderId().toString();
		LOGGER.info("Received PaymentCompletedEvent for order id : {}", orderId);

		try {
			PaymentResponseAvroModel paymentResponseAvroModel = paymentMessagingDataMapper
					.paymentCompletedEventToPaymentResponseAvroModel(domainEvent);

			kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(), 
					orderId, 
					paymentResponseAvroModel, 
					kafkaMessageHelper.getKafkaCallback(paymentServiceConfigData.getPaymentResponseTopicName(),
							paymentResponseAvroModel, 
							orderId, 
							"PaymentResponseAvroModel"));

			LOGGER.info("PaymentResponseAvroModel sent to kafka for order id : {}", orderId);
		} catch (Exception ex) {
			LOGGER.error("Error while sending PaymentResponseAvroModel message "
					+ "to kafka with order id : {} - ERROR : {}", orderId, ex.getMessage());
		}
	}
}
