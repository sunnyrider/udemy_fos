package com.food.ordering.system.restaurant.service.domain.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

public class OrderRejectedEvent extends OrderApprovalEvent {
	private final DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher;

	public OrderRejectedEvent(OrderApproval orderApproval, 
			RestaurantId restaurantId, 
			List<String> failureMessages,
			ZonedDateTime createdAt, 
			DomainEventPublisher<OrderRejectedEvent> rejectedEventDomainEventPublisher) {
		super(orderApproval, restaurantId, failureMessages, createdAt);
		orderRejectedEventDomainEventPublisher = rejectedEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		orderRejectedEventDomainEventPublisher.publish(this);
	}
}