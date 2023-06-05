package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;

@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApprovalResponseMessageListenerImpl.class);
	private final OrderApprovedSaga approvedSaga;

	public RestaurantApprovalResponseMessageListenerImpl(OrderApprovedSaga approvedSaga) {
		this.approvedSaga = approvedSaga;
	}

	@Override
	public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
		approvedSaga.process(restaurantApprovalResponse);
		LOGGER.info("Order is approved for order id : {}", restaurantApprovalResponse.getOrderId());
	}

	@Override
	public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
		OrderCancelledEvent cancelledEvent = approvedSaga.rollback(restaurantApprovalResponse);
		LOGGER.info("Order with id {} has been cancelled. Failure-messages : {}", 
				restaurantApprovalResponse.getOrderId(),
				String.join(Order.FAILURE_MESSAGE_DELIMITER, 
						restaurantApprovalResponse.getFailureMessages()));
		cancelledEvent.fire();
	}
}
