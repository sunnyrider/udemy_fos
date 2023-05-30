package com.food.ordering.system.order.service.domain.event;

import java.time.ZonedDateTime;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;

public class OrderCancelledEvent extends OrderEvent {
	private DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;

	public OrderCancelledEvent(Order order, ZonedDateTime createdAt,
			DomainEventPublisher<OrderCancelledEvent> cancelledEventDomainEventPublisher) {
		super(order, createdAt);
		orderCancelledEventDomainEventPublisher = cancelledEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		orderCancelledEventDomainEventPublisher.publish(this);
	}
}
