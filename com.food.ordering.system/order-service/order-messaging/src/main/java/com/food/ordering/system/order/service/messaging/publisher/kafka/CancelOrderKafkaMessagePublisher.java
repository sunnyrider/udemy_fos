package com.food.ordering.system.order.service.messaging.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

@Component
public class CancelOrderKafkaMessagePublisher implements OrderCancelledPaymentRequestMessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderKafkaMessagePublisher.class);

	private final OrderMessagingDataMapper orderMessagingDataMapper;
	private final OrderServiceConfigData orderServiceConfigData;
	private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
	private final KafkaMessageHelper orderKafkaMessageHelper;

	public CancelOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
			OrderServiceConfigData orderServiceConfigData,
			KafkaMessageHelper helper,
			KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer) {
		this.orderMessagingDataMapper = orderMessagingDataMapper;
		this.orderServiceConfigData = orderServiceConfigData;
		orderKafkaMessageHelper = helper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(OrderCancelledEvent domainEvent) {
		String orderId = domainEvent.getOrder().getId().getValue().toString();

		LOGGER.info("Received OrderCancelledEvent from order id : ", orderId);

		try {
			PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
					.orderCancelledEventToPaymentRequestAvroModel(domainEvent);

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
