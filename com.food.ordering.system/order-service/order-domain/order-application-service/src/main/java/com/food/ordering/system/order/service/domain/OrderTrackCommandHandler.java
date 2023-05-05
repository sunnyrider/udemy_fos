package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

@Component
public class OrderTrackCommandHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderTrackCommandHandler.class);

	public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {

		return null;
	}
}
