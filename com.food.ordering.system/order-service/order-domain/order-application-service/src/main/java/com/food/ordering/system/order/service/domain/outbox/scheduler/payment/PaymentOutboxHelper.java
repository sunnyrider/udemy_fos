package com.food.ordering.system.order.service.domain.outbox.scheduler.payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.outbox.model.payload.OrderPaymentEventPayload;
import com.food.ordering.system.order.service.domain.outbox.model.payload.OrderPaymentOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.repository.PaymentOutboxRepository;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import com.food.ordering.system.saga.order.SagaConstants;

@Component
public class PaymentOutboxHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentOutboxHelper.class);

	private final PaymentOutboxRepository paymentOutboxRepository;
	private final ObjectMapper objectMapper;

	public PaymentOutboxHelper(PaymentOutboxRepository outboxRepository,
			ObjectMapper  mapper) {
		this.paymentOutboxRepository = outboxRepository;
		objectMapper = mapper;
	}

	@Transactional(readOnly = true)
	public Optional<List<OrderPaymentOutboxMessage>> getPaymentOutboxMessageByOutboxStatusAnsSagaStatus(
			OutboxStatus outboxStatus,
			SagaStatus...sagaStatus) {
		return paymentOutboxRepository.findByTypeAndOutboxStatusAndSagaStatus(SagaConstants.ORDER_SAGA_NAME,
				outboxStatus, sagaStatus);
	}

	@Transactional(readOnly = true)
	public Optional<OrderPaymentOutboxMessage> getPaymentOutboxMessageBySagaIdAndSagaStatus(
			UUID sagaId,
			SagaStatus...sagaStatus) {
		return paymentOutboxRepository.findByTypeAndSagaIdAndSagaStatus(SagaConstants.ORDER_SAGA_NAME,
				sagaId, sagaStatus);
	}

    @Transactional
    public void savePaymentOutboxMessage(OrderPaymentEventPayload paymentEventPayload,
                                         OrderStatus orderStatus,
                                         SagaStatus sagaStatus,
                                         OutboxStatus outboxStatus,
                                         UUID sagaId) {
        save(OrderPaymentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(sagaId)
                .createdAt(paymentEventPayload.getCreatedAt())
                .type(SagaConstants.ORDER_SAGA_NAME)
                .payload(createPayload(paymentEventPayload))
                .orderStatus(orderStatus)
                .sagaStatus(sagaStatus)
                .outboxStatus(outboxStatus)
                .build());
    }

	@Transactional
	public void save(OrderPaymentOutboxMessage orderPaymentOutboxMessage) {
		OrderPaymentOutboxMessage response = paymentOutboxRepository.save(orderPaymentOutboxMessage);

		if (response == null) {
			LOGGER.error("Could not save OrderPaymentOutboxMessage with outbox id : {}",
					orderPaymentOutboxMessage.getId());
			throw new OrderDomainException("Could not save OrderPaymentOutboxMessage with outbox id : " +
					orderPaymentOutboxMessage.getId());
		}
		LOGGER.info("OrderPaymentOutboxMessage saved with outbox id : {}", 
				orderPaymentOutboxMessage.getId());
	}

    private String createPayload(OrderPaymentEventPayload paymentEventPayload) {
        try {
            return objectMapper.writeValueAsString(paymentEventPayload);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not create OrderPaymentEventPayload object for order id: {}",
                    paymentEventPayload.getOrderId(), e);
            throw new OrderDomainException("Could not create OrderPaymentEventPayload object for order id: " +
                    paymentEventPayload.getOrderId(), e);
        }
    }

	public void deletePaymentOutboxMessageByOutboxStatusAndSagaStatus(OutboxStatus outboxStatus,
			SagaStatus... sagaStatus) {
		paymentOutboxRepository.deleteByTypeAndOutboxStatusAndSagaStatus(
				SagaConstants.ORDER_SAGA_NAME, 
				outboxStatus, 
				sagaStatus);
	}
}
