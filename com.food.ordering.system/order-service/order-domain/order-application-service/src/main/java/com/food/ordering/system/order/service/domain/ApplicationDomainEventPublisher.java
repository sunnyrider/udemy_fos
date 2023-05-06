package com.food.ordering.system.order.service.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

@Component
public class ApplicationDomainEventPublisher implements 
			ApplicationEventPublisherAware, 
			DomainEventPublisher<OrderCreatedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDomainEventPublisher.class);

	public ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		applicationEventPublisher = publisher;
	}

	@Override
	public void publish(OrderCreatedEvent domainEvent) {
		applicationEventPublisher.publishEvent(domainEvent);
		LOGGER.info("OrderCreatedEvent is published for order id : {}", 
				domainEvent.getOrder().getId().getValue());
	}
}
