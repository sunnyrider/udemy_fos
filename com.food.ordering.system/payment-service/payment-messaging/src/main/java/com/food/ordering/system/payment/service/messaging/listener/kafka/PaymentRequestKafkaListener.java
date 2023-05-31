package com.food.ordering.system.payment.service.messaging.listener.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;

@Component
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRequestKafkaListener.class);

	private final PaymentRequestMessageListener paymentRequestMessageListener;
	private final PaymentMessagingDataMapper paymentMessagingDataMapper;

	public PaymentRequestKafkaListener(PaymentRequestMessageListener paymentRequestMessageListener,
			PaymentMessagingDataMapper paymentMessagingDataMapper) {
		super();
		this.paymentRequestMessageListener = paymentRequestMessageListener;
		this.paymentMessagingDataMapper = paymentMessagingDataMapper;
	}

	@Override
	@KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}",
					topics = "${payment.service.payment-request-topic-name}")
	public void receive(@Payload List<PaymentRequestAvroModel> messages, 
			@Header(KafkaHeaders.RECEIVED_KEY) List<String> keys, 
			@Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
			@Header(KafkaHeaders.OFFSET) List<Long> offsets) {

		LOGGER.info("{} numer of payment requests recieved withs key : {}"
				+ ", partitions : {} and offsets : {}", 
				messages.size(), 
				keys.toArray(), 
				partitions.toString(), 
				offsets.toString());

		messages.forEach(paymentRequestAvroModel -> {
			if (PaymentOrderStatus.PENDING == paymentRequestAvroModel.getPaymentOrderStatus()) {
				LOGGER.info("Processing payment for order id : {}", paymentRequestAvroModel.getOrderId());
				paymentRequestMessageListener.completePayment(
						paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
			} else if (PaymentOrderStatus.CANCELLED == paymentRequestAvroModel.getPaymentOrderStatus()) {
				LOGGER.info("Cancelling payment for order id : {}", paymentRequestAvroModel.getOrderId());
				paymentRequestMessageListener.cancelPayment(
						paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
			}
		});
	}

}
