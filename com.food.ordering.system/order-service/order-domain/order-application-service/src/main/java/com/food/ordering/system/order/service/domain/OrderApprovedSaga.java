package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.saga.SagaStep;

@Component
public class OrderApprovedSaga implements SagaStep<RestaurantApprovalResponse, EmptyEvent, OrderCancelledEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderApprovedSaga.class);

	private final OrderDomainService orderDomainService;
	private OrderSagaHelper orderSagaHelper;
	private OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;

	public OrderApprovedSaga(OrderDomainService orderDomainService, OrderSagaHelper orderSagaHelper,
			OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher) {
		this.orderDomainService = orderDomainService;
		this.orderSagaHelper = orderSagaHelper;
		this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
	}

	@Override
	@Transactional
	public EmptyEvent process(RestaurantApprovalResponse response) {
		LOGGER.info("Approving order with id : {}", response.getOrderId());
		Order order = orderSagaHelper.findOrder(response.getOrderId());
		orderDomainService.approveOrder(order);
		orderSagaHelper.saveOrder(order);
		LOGGER.info("Order with id : {} is approved", order.getId().getValue());
		
		return EmptyEvent.INSTANCE;
	}

	@Override
	@Transactional
	public OrderCancelledEvent rollback(RestaurantApprovalResponse response) {
		LOGGER.info("Cancelling order with id : {}", response.getOrderId());
		Order order = orderSagaHelper.findOrder(response.getOrderId());
		OrderCancelledEvent cancelledEvent = orderDomainService.cancelOrderPayment(
				order, response.getFailureMessages(), orderCancelledPaymentRequestMessagePublisher);
		orderSagaHelper.saveOrder(order);
		LOGGER.info("Order with id : {} is cancelling", order.getId().getValue());
		cancelledEvent.fire();

		return cancelledEvent;
	}

}
