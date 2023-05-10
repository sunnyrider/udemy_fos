package com.food.ordering.system.order.service.domain;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderApplicationServiceImpl.class); 

	private final OrderCreateCommandHandler orderCreateCommandHandler;
	private final OrderTrackCommandHandler orderTrackCommandHandler;

	public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
			OrderTrackCommandHandler orderTrackCommandHandler) {
		super();
		this.orderCreateCommandHandler = orderCreateCommandHandler;
		this.orderTrackCommandHandler = orderTrackCommandHandler;
	}

	@Override
	public CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand) {
		return orderCreateCommandHandler.createOrder(createOrderCommand);
	}

	@Override
	public TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery) {
		return orderTrackCommandHandler.trackOrder(trackOrderQuery);
	}
}
