package com.food.ordering.system.payment.service.messaging.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentStatus;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

@Component
public class PaymentMessagingDataMapper {

	public PaymentResponseAvroModel paymentCompletedEventToPaymentResponseAvroModel(
			PaymentCompletedEvent paymentCompletedEvent) {
		return paymentAventToPaymentResponseAvroModel(paymentCompletedEvent);
	}

	public PaymentResponseAvroModel paymentCancelledEventToPaymentResponseAvroModel(
			PaymentCancelledEvent paymentCancelledEvent) {
		return paymentAventToPaymentResponseAvroModel(paymentCancelledEvent);
	}

	public PaymentResponseAvroModel paymentFailedRventToPaymentResponseAvroModel(
			PaymentFailedEvent paymentFailedEvent) {
		return paymentAventToPaymentResponseAvroModel(paymentFailedEvent);
	}

	public PaymentRequest paymentRequestAvroModelToPaymentRequest(
			PaymentRequestAvroModel paymentRequestAvroModel) {
		return PaymentRequest.builder()
				.id(paymentRequestAvroModel.getId().toString())
				.sagaId(paymentRequestAvroModel.getSagaId().toString())
				.customerId(paymentRequestAvroModel.getCustomerId().toString())
				.orderId(paymentRequestAvroModel.getOrderId().toString())
				.price(paymentRequestAvroModel.getPrice())
				.createdAt(paymentRequestAvroModel.getCreatedAt())
				.paymentOrderStatus(PaymentOrderStatus.valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
				.build();
	}

	private PaymentResponseAvroModel paymentAventToPaymentResponseAvroModel(
			PaymentEvent paymentEvent) {
		return PaymentResponseAvroModel.newBuilder()
				.setId(UUID.randomUUID())
				.setSagaId(null)
				.setPaymentId(paymentEvent.getPayment().getId().getValue())
				.setCustomerId(paymentEvent.getPayment().getCustomerId().getValue())
				.setOrderId(paymentEvent.getPayment().getOrderId().getValue())
				.setPrice(paymentEvent.getPayment().getPrice().getAmount())
				.setCreatedAt(paymentEvent.getCreatedAt().toInstant())
				.setPaymentStatus(PaymentStatus.valueOf(paymentEvent.getPayment().getPaymentStatus().name()))
				.setFailureMessages(paymentEvent.getFailureMessages())
				.build();
	}
}
