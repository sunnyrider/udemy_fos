package com.food.ordering.system.order.service.messaging.listener.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;

@Component
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApprovalResponseKafkaListener.class);

	private final OrderMessagingDataMapper orderMessagingDataMapper;
	private final RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener;

	public RestaurantApprovalResponseKafkaListener(OrderMessagingDataMapper orderMessagingDataMapper,
			RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener) {
		this.orderMessagingDataMapper = orderMessagingDataMapper;
		this.restaurantApprovalResponseMessageListener = restaurantApprovalResponseMessageListener;
	}

	@Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
    				topics = "${order-service.restaurant-approval-response-topic-name}")
	public void receive(List<RestaurantApprovalResponseAvroModel> messages, 
			@Header(KafkaHeaders.RECEIVED_KEY) List<String> keys, 
			@Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
			@Header(KafkaHeaders.OFFSET) List<Long> offsets) {

		LOGGER.info("{} number of restaurant approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

		messages.forEach(avroModel -> {
			if (OrderApprovalStatus.APPROVED == avroModel.getOrderApprovalStatus()) {
				LOGGER.info("Processing approved order for order id: {}",
						avroModel.getOrderId());
				restaurantApprovalResponseMessageListener.orderApproved(
						orderMessagingDataMapper.approvalResponseAvroModelToApprovalResponse(avroModel));
			} else if (OrderApprovalStatus.REJECTED == avroModel.getOrderApprovalStatus()) {
				LOGGER.info("Processing rejected order for order id: {}, with failure messages: {}",
						avroModel.getOrderId(),
                        String.join(Order.FAILURE_MESSAGE_DELIMITER,
                        		avroModel.getFailureMessages()));
                restaurantApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(avroModel));
			}
		});

	}

}
