package com.food.ordering.system.order.service.messaging.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

@Component
public class OrderMessagingDataMapper {

	public PaymentResponse paymentResponseAvroModelToPaymentResponse(
			PaymentResponseAvroModel avroModel) {
		return PaymentResponse.builder()
				.withId(avroModel.getId().toString())
				.withSagaId(avroModel.getSagaId().toString())
				.withPaymentId(avroModel.getPaymentId().toString())
				.withCustomerId(avroModel.getCustomerId().toString())
				.withOrderId(avroModel.getOrderId().toString())
				.withPrice(avroModel.getPrice())
				.withCreatedAt(avroModel.getCreatedAt())
				.withPaymentStatus(PaymentStatus.valueOf(avroModel.getPaymentStatus().name()))
				.withFailureMessages(avroModel.getFailureMessages())
				.build();
	}

	public RestaurantApprovalRequestAvroModel orderPaidEventToRestaurantApprovalRequestAvroModel(
			OrderPaidEvent orderpaidEvent) {
		Order order = orderpaidEvent.getOrder();
		return RestaurantApprovalRequestAvroModel.newBuilder()
				.setId(UUID.randomUUID())
				.setSagaId(null)
				.setOrderId(order.getId().getValue())
				.setRestaurantId(order.getRestaurantId().getValue())
				.setRestaurantOrderStatus(RestaurantOrderStatus.valueOf(order.getOrderStatus().name()))
				.setProducts(order.getItems().stream()
						.map(item -> com.food.ordering.system.kafka.order.avro.model.Product.newBuilder()
								.setId(item.getProduct().getId().getValue().toString())
								.setQuantity(item.getQuantity())
						.build()).toList())
				.setPrice(order.getPrice().getAmount())
				.setCreatedAt(orderpaidEvent.getCreatedAt().toInstant())
				.setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
				.build();
	}

	public RestaurantApprovalResponse approvalResponseAvroModelToApprovalResponse(
			RestaurantApprovalResponseAvroModel avroModel) {
		return RestaurantApprovalResponse.builder()
				.withId(avroModel.getId().toString())
				.withSagaId(avroModel.getSagaId().toString())
				.withRestaurantId(avroModel.getRestaurantId().toString())
				.withOrderId(avroModel.getOrderId().toString())
				.withCreatedAt(avroModel.getCreatedAt())
				.withOrderApprovalStatus(OrderApprovalStatus.valueOf(avroModel.getOrderApprovalStatus().name()))
				.withFailureMessages(avroModel.getFailureMessages())
				.build();
	}

	public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
		Order order = orderCreatedEvent.getOrder();
		return PaymentRequestAvroModel.newBuilder()
				.setId(UUID.randomUUID())
				.setSagaId(null)
				.setCustomerId(order.getCustomerId().getValue())
				.setOrderId(order.getId().getValue())
				.setPrice(order.getPrice().getAmount())
				.setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
				.setPaymentOrderStatus(PaymentOrderStatus.PENDING)
				.build();
	}

	public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
		Order order = orderCancelledEvent.getOrder();
		return PaymentRequestAvroModel.newBuilder()
				.setId(UUID.randomUUID())
				.setSagaId(null)
				.setCustomerId(order.getCustomerId().getValue())
				.setOrderId(order.getId().getValue())
				.setPrice(order.getPrice().getAmount())
				.setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
				.setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
				.build();
	}
}
