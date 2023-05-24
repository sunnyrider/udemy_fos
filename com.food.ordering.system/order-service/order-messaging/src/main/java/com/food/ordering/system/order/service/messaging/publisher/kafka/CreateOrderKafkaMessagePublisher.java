package com.food.ordering.system.order.service.messaging.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

@Component
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderKafkaMessagePublisher.class);

	private final OrderMessagingDataMapper orderMessagingDataMapper;
	private final OrderServiceConfigData orderServiceConfigData;
	private final OrderKafkaMessageHelper orderKafkaMessageHelper;
	private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;

	public CreateOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
			OrderServiceConfigData orderServiceConfigData,
			OrderKafkaMessageHelper helper,
			KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer) {
		this.orderMessagingDataMapper = orderMessagingDataMapper;
		this.orderServiceConfigData = orderServiceConfigData;
		orderKafkaMessageHelper = helper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(OrderCreatedEvent domainEvent) {
		String orderId = domainEvent.getOrder().getId().getValue().toString();

		LOGGER.info("Received OrderCreatedEvent from order id : ", orderId);

		try {
			PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
					.orderCreatedEventToPaymentRequestAvroModel(domainEvent);

			kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(), orderId, paymentRequestAvroModel,
					orderKafkaMessageHelper.getKafkaCallback(
							orderServiceConfigData.getPaymentResponseTopicName(), 
							paymentRequestAvroModel, orderId, "PaymentRequestAvroModel"));

			LOGGER.info("PaymentRequestAvroModel sent to Kafka for order id : {}", orderId);
		} catch (Exception ex) {
			LOGGER.error("Error while sending PaymentRequestAvroModel message "
					+ "to Kafka with order id : {}. ERROR : {}", orderId, ex.getMessage());
		}
	}
}
