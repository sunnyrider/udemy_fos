package com.food.ordering.system.restaurant.service.messaging.mapper;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

@Component
public class RestaurantMessagingDataMapper {

	public RestaurantApprovalResponseAvroModel orderApprovedEventToRestaurantApprovalResponseAvroModel(
			OrderApprovedEvent event) {
		return RestaurantApprovalResponseAvroModel.newBuilder()
		.setId(UUID.randomUUID())
		.setSagaId(null)
		.setOrderId(event.getOrderApproval().getOrderId().getValue())
		.setRestaurantId(event.getRestaurantId().getValue())
		.setCreatedAt(event.getCreatedAt().toInstant())
		.setOrderApprovalStatus(OrderApprovalStatus.valueOf(event.getOrderApproval().getApprovalStatus().name()))
		.setFailureMessages(event.getFailureMessages())
		.build();
	}

	public RestaurantApprovalResponseAvroModel orderRejectedEventToRestaurantApprovalResponseAvroModel(
			OrderRejectedEvent event) {
		return RestaurantApprovalResponseAvroModel.newBuilder()
		.setId(UUID.randomUUID())
		.setSagaId(null)
		.setOrderId(event.getOrderApproval().getOrderId().getValue())
		.setRestaurantId(event.getRestaurantId().getValue())
		.setCreatedAt(event.getCreatedAt().toInstant())
		.setOrderApprovalStatus(OrderApprovalStatus.valueOf(event.getOrderApproval().getApprovalStatus().name()))
		.setFailureMessages(event.getFailureMessages())
		.build();
	}

	public RestaurantApprovalRequest
    restaurantApprovalRequestAvroModelToRestaurantApproval(RestaurantApprovalRequestAvroModel
                                                                   restaurantApprovalRequestAvroModel) {
        return RestaurantApprovalRequest.builder()
                .id(restaurantApprovalRequestAvroModel.getId().toString())
                .sagaId(restaurantApprovalRequestAvroModel.getSagaId().toString())
                .restaurantId(restaurantApprovalRequestAvroModel.getRestaurantId().toString())
                .orderId(restaurantApprovalRequestAvroModel.getOrderId().toString())
                .restaurantOrderStatus(RestaurantOrderStatus.valueOf(restaurantApprovalRequestAvroModel
                        .getRestaurantOrderStatus().name()))
                .products(restaurantApprovalRequestAvroModel.getProducts()
                        .stream().map(avroModel ->
                                Product.builder()
                                        .productId(new ProductId(UUID.fromString(avroModel.getId())))
                                        .quantity(avroModel.getQuantity())
                                        .build())
                        .collect(Collectors.toList()))
                .price(restaurantApprovalRequestAvroModel.getPrice())
                .createdAt(restaurantApprovalRequestAvroModel.getCreatedAt())
                .build();
    }

//    public RestaurantApprovalResponseAvroModel
//    orderEventPayloadToRestaurantApprovalResponseAvroModel(String sagaId, OrderEventPayload orderEventPayload) {
//        return RestaurantApprovalResponseAvroModel.newBuilder()
//                .setId(UUID.randomUUID())
//                .setSagaId(UUID.fromString(sagaId))
//                .setOrderId(UUID.fromString(orderEventPayload.getOrderId()))
//                .setRestaurantId(UUID.fromString(orderEventPayload.getRestaurantId()))
//                .setCreatedAt(orderEventPayload.getCreatedAt().toInstant())
//                .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderEventPayload.getOrderApprovalStatus()))
//                .setFailureMessages(orderEventPayload.getFailureMessages())
//                .build();
//    }
}
