package com.food.ordering.system.restaurant.service.domain;

import static com.food.ordering.system.domain.DomainConstants.UTC;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

public class RestaurantDomainServiceImpl implements RestaurantDomainService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantDomainServiceImpl.class);

    @Override
	public OrderApprovalEvent validateOrder(Restaurant restaurant, 
			List<String> failureMessages,
			DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
			DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher) {

    	restaurant.validateOrder(failureMessages);
        LOGGER.info("Validating order with id: {}", restaurant.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            LOGGER.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    orderApprovedEventDomainEventPublisher);
        } else {
            LOGGER.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    orderRejectedEventDomainEventPublisher);
        }
    }
}
