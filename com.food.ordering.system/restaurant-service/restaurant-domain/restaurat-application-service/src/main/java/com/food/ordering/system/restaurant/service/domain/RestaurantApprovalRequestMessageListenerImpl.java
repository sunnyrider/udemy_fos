package com.food.ordering.system.restaurant.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.ports.input.message.listener.RestaurantApprovalRequestMessageListener;

@Service
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {
	private Logger LOGGER = LoggerFactory.getLogger(RestaurantApprovalRequestMessageListenerImpl.class);

	private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;

	public RestaurantApprovalRequestMessageListenerImpl(
			RestaurantApprovalRequestHelper restaurantApprovalRequestHelper) {
		this.restaurantApprovalRequestHelper = restaurantApprovalRequestHelper;
	}

	@Override
	public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
		OrderApprovalEvent orderApprovalEvent = 
				restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
	}
}
