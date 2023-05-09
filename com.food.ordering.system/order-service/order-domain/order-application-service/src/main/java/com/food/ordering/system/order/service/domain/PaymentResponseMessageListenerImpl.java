package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;

@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResponseMessageListenerImpl.class);

	@Override
	public void paymentCompleted(PaymentResponse paymentResponse) {
		
	}

	@Override
	public void paymentCancelled(PaymentResponse paymentResponse) {
		
	}

}
