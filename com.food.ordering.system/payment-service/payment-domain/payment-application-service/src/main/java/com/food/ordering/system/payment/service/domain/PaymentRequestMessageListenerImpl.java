package com.food.ordering.system.payment.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;

@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRequestMessageListenerImpl.class);

    private final PaymentRequestHelper paymentRequestHelper;

    public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper) {
        this.paymentRequestHelper = paymentRequestHelper;
    }

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        paymentRequestHelper.persistPayment(paymentRequest);
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        paymentRequestHelper.persistCancelPayment(paymentRequest);
    }
}
