package com.food.ordering.system.order.service.messaging.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderKafkaMessagePublisher.class);

	private final OrderMessagingDataMapper orderMessagingDataMapper;
	private final OrderServiceConfigData orderServiceConfigData;
	private final KafkaMessageHelper orderKafkaMessageHelper;
	private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;

	public PayOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
			OrderServiceConfigData orderServiceConfigData, KafkaMessageHelper orderKafkaMessageHelper,
			KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer) {
		this.orderMessagingDataMapper = orderMessagingDataMapper;
		this.orderServiceConfigData = orderServiceConfigData;
		this.orderKafkaMessageHelper = orderKafkaMessageHelper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(OrderPaidEvent paidEvent) {
		String orderId = paidEvent.getOrder().getId().getValue().toString();

		try {
			RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel = orderMessagingDataMapper
					.orderPaidEventToRestaurantApprovalRequestAvroModel(paidEvent);

			kafkaProducer.send(orderServiceConfigData.getRestaurantApprovalRequestTopicName(), orderId,
					restaurantApprovalRequestAvroModel,
					orderKafkaMessageHelper.getKafkaCallback(
							orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
							restaurantApprovalRequestAvroModel, orderId, "RestaurantApprovalRequestAvroModel"));
			LOGGER.info("RestaurantApprovalRequestAvroModel sent to Kafka for order id : {}", orderId);
		} catch (Exception ex) {
			LOGGER.error("Error while sending RestaurantApprovalRequestAvroModel message "
					+ "to Kafka with order id : {}, error : {}", orderId, ex.getMessage());
		}
	}

}
