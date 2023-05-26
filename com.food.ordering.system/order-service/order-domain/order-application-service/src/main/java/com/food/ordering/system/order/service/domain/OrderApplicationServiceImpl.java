package com.food.ordering.system.order.service.domain;

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

	/**
	 * Spring.Boot Version 2.6.7
	 */
	@Override
	public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
		return orderCreateCommandHandler.createOrder(createOrderCommand);
	}

	@Override
	public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
		return orderTrackCommandHandler.trackOrder(trackOrderQuery);
	}

	/**
	 * Spring.Boot Version 3.0.6
	 */
//	@Override
//	public CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand) {
//		return orderCreateCommandHandler.createOrder(createOrderCommand);
//	}
//
//	@Override
//	public TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery) {
//		return orderTrackCommandHandler.trackOrder(trackOrderQuery);
//	}
}
