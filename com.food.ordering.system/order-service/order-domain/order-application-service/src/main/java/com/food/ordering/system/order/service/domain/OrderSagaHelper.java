package com.food.ordering.system.order.service.domain;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;

public class OrderSagaHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderSagaHelper.class);

	private final OrderRepository orderRepository;

	public OrderSagaHelper(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	Order findOrder(String orderId) {
		Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
		if (orderResponse.isEmpty()) {
			LOGGER.error("Order with id : {} could not be found.", orderId);
			throw new OrderNotFoundException("Order with id : " + orderId + " could not be found.");
		}
		return orderResponse.get();
	}

	void saveOrder(Order order) {
		orderRepository.save(order);
	}
}
