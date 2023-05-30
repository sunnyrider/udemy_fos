package com.food.ordering.system.order.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.ordering.system.domain.DomainConstants;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;

public class OrderDomainServiceImpl implements OrderDomainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDomainServiceImpl.class);

	@Override
	public OrderCreatedEvent validateAndInitiateOrder(Order order, 
			Restaurant restaurant,
			DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
		validateRestaurant(restaurant);
		setOrderProductInformation(order, restaurant);
		order.validateOrder();
		order.initializeOrder();

		LOGGER.info("Order with id : {} is initiated", order.getId().getValue());
		return new OrderCreatedEvent(order, 
				ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
				orderCreatedEventDomainEventPublisher);
	}

	private void validateRestaurant(Restaurant restaurant) {
		if (!restaurant.isActive()) {
			throw new OrderDomainException("Restaurant with id : " + restaurant.getId().getValue()
					+ " is currently not active!");
		}
	}

	private void setOrderProductInformation(Order order, Restaurant restaurant) {
		order.getItems().forEach(orderItem -> 
		restaurant.getProducts().forEach(restaurantProduct -> {
			Product currentProduct = orderItem.getProduct();
			if (currentProduct.equals(restaurantProduct)) {
				currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
						restaurantProduct.getPrice());
			}
		}));
	}

	@Override
	public OrderPaidEvent payOrder(Order order,
			DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
		order.pay();
		LOGGER.info("Order with id : {} has been paid", order.getId().getValue());
		return new OrderPaidEvent(order,
				ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
				orderPaidEventDomainEventPublisher);
	}

	@Override
	public void approveOrder(Order order) {
		order.approve();
		LOGGER.info("Order with id : {} has been approved", order.getId().getValue());
	}

	@Override
	public OrderCancelledEvent cancelOrderPayment(Order order, 
			List<String> failureMessages,
			DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
		order.initCancel(failureMessages);
		LOGGER.info("Order payment is cancelling for order id : {}", order.getId().getValue());
		return new OrderCancelledEvent(order, 
				ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
				orderCancelledEventDomainEventPublisher);
	}

	@Override
	public void cancelOrder(Order order, List<String> failureMessages) {
		order.cancel(failureMessages);
		LOGGER.info("Order for order id : {} has been cancelled", order.getId().getValue());
	}
}
