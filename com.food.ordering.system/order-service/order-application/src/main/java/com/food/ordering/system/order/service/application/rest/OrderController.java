package com.food.ordering.system.order.service.application.rest;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	private final OrderApplicationService orderApplicationService;

	public OrderController(OrderApplicationService orderApplicationService) {
		this.orderApplicationService = orderApplicationService;
	}

	public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
		LOGGER.info("Creating order for customer : {} at restaurant : {}", 
				createOrderCommand.getCustomerId(), createOrderCommand.getRestaurantId());
		CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
		LOGGER.info("Order created with tracking id : {}", createOrderResponse.getOrderTrackingId());

		return ResponseEntity.ok(createOrderResponse);
	}

	@GetMapping("/{trackingId}")
	public ResponseEntity<TrackOrderResponse> getOrderTrackingId(@PathVariable UUID trackingId) {
		TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(
				TrackOrderQuery.builder().withOrderTrackingId(trackingId).build());
		LOGGER.info("Returning order status with tracking id : {}", trackOrderResponse.getOrderTrackingId());
		return ResponseEntity.ok(trackOrderResponse);
	}
}
