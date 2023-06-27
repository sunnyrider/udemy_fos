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

    private final OrderApprovalSaga orderApprovalSaga;

    public RestaurantApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
        this.orderApprovalSaga = orderApprovalSaga;
    }

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        orderApprovalSaga.process(restaurantApprovalResponse);
        LOGGER.info("Order is approved for order id: {}", restaurantApprovalResponse.getOrderId());
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
          orderApprovalSaga.rollback(restaurantApprovalResponse);
          LOGGER.info("Order Approval Saga rollback operation is completed for order id: {} with failure messages: {}",
                  restaurantApprovalResponse.getOrderId(),
                  String.join(Order.FAILURE_MESSAGE_DELIMITER, restaurantApprovalResponse.getFailureMessages()));
    }
}