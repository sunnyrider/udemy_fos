package com.food.ordering.system.restaurant.service.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.exception.RestaurantNotFoundException;
import com.food.ordering.system.restaurant.service.domain.mapper.RestaurantDataMapper;
import com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderApprovalMessagePublisher;
import com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.food.ordering.system.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;
import com.food.ordering.system.restaurant.service.domain.ports.output.repository.RestaurantRepository;

//@Slf4j
@Component
public class RestaurantApprovalRequestHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApprovalRequestHelper.class);

	private final RestaurantDomainService restaurantDomainService;
	private final RestaurantDataMapper restaurantDataMapper;
	private final RestaurantRepository restaurantRepository;
	private final OrderApprovalRepository orderApprovalRepository;
	private final OrderApprovalMessagePublisher orderApprovalMessagePublisher;
	private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

	public RestaurantApprovalRequestHelper(RestaurantDomainService restaurantDomainService,
			RestaurantDataMapper restaurantDataMapper, 
			RestaurantRepository restaurantRepository,
			OrderApprovalRepository orderApprovalRepository, 
			OrderRejectedMessagePublisher orderRejectedMessagePublisher, 
			OrderApprovalMessagePublisher orderApprovalMessagePublisher) {
		this.restaurantDomainService = restaurantDomainService;
		this.restaurantDataMapper = restaurantDataMapper;
		this.restaurantRepository = restaurantRepository;
		this.orderApprovalRepository = orderApprovalRepository;
		this.orderApprovalMessagePublisher = orderApprovalMessagePublisher;
		this.orderRejectedMessagePublisher = orderRejectedMessagePublisher;
	}

	@Transactional
	public OrderApprovalEvent persistOrderApproval(RestaurantApprovalRequest restaurantApprovalRequest) {
//		if (publishIfOutboxMessageProcessed(restaurantApprovalRequest)) {
//			LOGGER.info("An outbox message with saga id: {} already saved to database!",
//					restaurantApprovalRequest.getSagaId());
//			return;
//		}

		LOGGER.info("Processing restaurant approval for order id: {}", restaurantApprovalRequest.getOrderId());
		List<String> failureMessages = new ArrayList<>();
		Restaurant restaurant = findRestaurant(restaurantApprovalRequest);

		OrderApprovalEvent orderApprovalEvent = restaurantDomainService
				.validateOrder(
						restaurant, 
						failureMessages, 
						orderApprovalMessagePublisher, 
						orderRejectedMessagePublisher);

		orderApprovalRepository.save(restaurant.getOrderApproval());
		return orderApprovalEvent;

//		orderOutboxHelper.saveOrderOutboxMessage(
//				restaurantDataMapper.orderApprovalEventToOrderEventPayload(orderApprovalEvent),
//				orderApprovalEvent.getOrderApproval().getApprovalStatus(), OutboxStatus.STARTED,
//				UUID.fromString(restaurantApprovalRequest.getSagaId()));

	}

	private Restaurant findRestaurant(RestaurantApprovalRequest restaurantApprovalRequest) {
		Restaurant restaurant = restaurantDataMapper.restaurantApprovalRequestToRestaurant(restaurantApprovalRequest);
		Optional<Restaurant> restaurantResult = restaurantRepository.findRestaurantInformation(restaurant);
		if (restaurantResult.isEmpty()) {
			LOGGER.error("Restaurant with id " + restaurant.getId().getValue() + " not found!");
			throw new RestaurantNotFoundException(
					"Restaurant with id " + restaurant.getId().getValue() + " not found!");
		}

		Restaurant restaurantEntity = restaurantResult.get();
		restaurant.setActive(restaurantEntity.isActive());
		restaurant.getOrderDetail().getProducts()
				.forEach(product -> restaurantEntity.getOrderDetail().getProducts().forEach(p -> {
					if (p.getId().equals(product.getId())) {
						product.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
					}
				}));
		restaurant.getOrderDetail().setId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())));

		return restaurant;
	}

//	private boolean publishIfOutboxMessageProcessed(RestaurantApprovalRequest restaurantApprovalRequest) {
//		Optional<OrderOutboxMessage> orderOutboxMessage = orderOutboxHelper
//				.getCompletedOrderOutboxMessageBySagaIdAndOutboxStatus(
//						UUID.fromString(restaurantApprovalRequest.getSagaId()), OutboxStatus.COMPLETED);
//		if (orderOutboxMessage.isPresent()) {
//			restaurantApprovalResponseMessagePublisher.publish(orderOutboxMessage.get(),
//					orderOutboxHelper::updateOutboxStatus);
//			return true;
//		}
//		return false;
//	}
}
