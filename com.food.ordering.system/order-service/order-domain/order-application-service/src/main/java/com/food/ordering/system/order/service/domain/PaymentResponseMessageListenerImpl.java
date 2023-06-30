package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;

@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResponseMessageListenerImpl.class);
	private final OrderPaymentSaga orderPaymentSaga;

	public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
		this.orderPaymentSaga = orderPaymentSaga;
	}

	@Override
	public void paymentCompleted(PaymentResponse paymentResponse) {
		orderPaymentSaga.process(paymentResponse);
		LOGGER.info("Order Payment Saga process operation is completed for order id : {}", 
				paymentResponse.getOrderId());
	}

	@Override
	public void paymentCancelled(PaymentResponse paymentResponse) {
		orderPaymentSaga.rollback(paymentResponse);
		LOGGER.info("Order Approval Saga rollback operation is completed for order id "
				+ ": {} with failure messages : {}", paymentResponse.getOrderId(),
				String.join(Order.FAILURE_MESSAGE_DELIMITER, paymentResponse.getFailureMessages()));
	}

}
