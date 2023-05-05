package com.food.ordering.system.order.service.domain;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

@Component
public class OrderCreateCommandHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateCommandHandler.class);

	private final OrderDomainService orderDomainService;
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final RestaurantRepository restaurantRepository;
	private final OrderDataMapper orderDataMapper;

	public OrderCreateCommandHandler(OrderDomainService orderDomainService, 
			OrderRepository orderRepository,
			CustomerRepository customerRepository, 
			RestaurantRepository restaurantRepository,
			OrderDataMapper orderDataMapper) {
		super();
		this.orderDomainService = orderDomainService;
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.restaurantRepository = restaurantRepository;
		this.orderDataMapper = orderDataMapper;
	}

	@Transactional
	public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
		checkCustomer(createOrderCommand.getCustomerId());
		Restaurant restaurant = checkRestaurant(createOrderCommand);
		Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);

		OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);

		Order result = saveOrder(order);
		LOGGER.info("Order is created with id : {}", result.getId().getValue());

		return orderDataMapper.orderToCreateOrderResponse(result);
	}

	private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
		Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);

		if (optionalRestaurant.isEmpty()) {
			LOGGER.warn("Could not find restaurant with id : {}", createOrderCommand.getRestaurantId());
			throw new OrderDomainException("Could not find restaurant with id : " 
					+ createOrderCommand.getRestaurantId());
		}

		return optionalRestaurant.get();
	}

	private void checkCustomer(UUID customerId) {
		Optional<Customer> customer = customerRepository.findCustomer(customerId);
		if (customer.isEmpty()) {
			LOGGER.warn("Could not find customer with customer id : {}", customerId);
			throw new OrderDomainException("Could not find customer with customer id : " + customerId);
		}
	}

	private Order saveOrder(Order order) {
		Order result = orderRepository.save(order);

		if (result == null) {
			LOGGER.error("Could not save order!");
			throw new OrderDomainException("Could not save order!");
		}

		LOGGER.info("Order is saved with id : {}", result.getId().getValue());
		return result;
	}
}
