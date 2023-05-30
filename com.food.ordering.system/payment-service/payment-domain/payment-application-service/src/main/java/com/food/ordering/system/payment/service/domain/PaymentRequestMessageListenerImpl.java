package com.food.ordering.system.payment.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;

@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRequestMessageListenerImpl.class);

	private final PaymentRequestHelper paymentRequestHelper;

	public PaymentRequestMessageListenerImpl(PaymentRequestHelper requestHelper) {
		paymentRequestHelper = requestHelper;
	}

	@Override
	public void completePayment(PaymentRequest paymentRequest) {
		PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
		fireEvent(paymentEvent);
	}

	@Override
	public void cancelPayment(PaymentRequest paymentRequest) {
		PaymentEvent paymentEvent = paymentRequestHelper.persistPaymentCancel(paymentRequest);
		fireEvent(paymentEvent);
	}

	private void fireEvent(PaymentEvent paymentEvent) {
		LOGGER.info("Publishing payment event with payment id : {} and order id : {}", 
				paymentEvent.getPayment().getId().getValue(), 
				paymentEvent.getPayment().getOrderId().getValue());
		paymentEvent.fire();
	}
}
