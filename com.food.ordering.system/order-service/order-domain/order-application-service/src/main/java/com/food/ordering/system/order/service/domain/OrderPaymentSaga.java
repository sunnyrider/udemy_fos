package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.saga.SagaStep;

@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaymentSaga.class);

	private final OrderDomainService orderDomainService;
	private OrderSagaHelper orderSagaHelper;
	private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher;

	public OrderPaymentSaga(OrderDomainService orderDomainService, OrderSagaHelper sagaHelper,
			OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher) {
		this.orderDomainService = orderDomainService;
		orderSagaHelper = sagaHelper;
		this.orderPaidRestaurantRequestMessagePublisher = orderPaidRestaurantRequestMessagePublisher;
	}

	@Override
	@Transactional
	public OrderPaidEvent process(PaymentResponse data) {
		LOGGER.info("Completing payment for order with is : {}", data.getOrderId());
		Order order = orderSagaHelper.findOrder(data.getOrderId());
		OrderPaidEvent event = orderDomainService.payOrder(order, orderPaidRestaurantRequestMessagePublisher);
		orderSagaHelper.saveOrder(order);
		LOGGER.info("Order with id : {} ist paid.", order.getId().getValue());

		return event;
	}

	@Override
	@Transactional
	public EmptyEvent rollback(PaymentResponse data) {
		LOGGER.info("Cancelling order with id : {}", data.getOrderId());
		Order order = orderSagaHelper.findOrder(data.getOrderId());
		orderDomainService.cancelOrder(order, data.getFailureMessages());
		orderSagaHelper.saveOrder(order);
		LOGGER.info("Order with id : {} has been cancelled", order.getId().getValue());

		return EmptyEvent.INSTANCE;
	}
}
