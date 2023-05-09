package com.food.ordering.system.order.service.domain;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

@Component
public class OrderTrackCommandHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderTrackCommandHandler.class);

	private final OrderDataMapper orderDataMapper;
	private final OrderRepository orderRepository;

	public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
		this.orderDataMapper = orderDataMapper;
		this.orderRepository = orderRepository;
	}

	public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
		Optional<Order> orderResult = orderRepository
				.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
		if (orderResult.isEmpty()) {
			LOGGER.warn("Could not find order with tracking id : {}", trackOrderQuery.getOrderTrackingId());
			throw new OrderNotFoundException("Could not find order with tracking id : " 
						+ trackOrderQuery.getOrderTrackingId());
		}

		return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
	}
}
