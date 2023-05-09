package com.food.ordering.system.order.service.domain.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

@Component
public class OrderDataMapper {

	public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
		return Restaurant.builder()
				.withRestaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.withProducts(createOrderCommand.getItems().stream()
						.map(prod -> new Product(new ProductId(prod.getProductId())))
						.toList()
				).build();
	}

	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		return Order.builder()
				.withCustomerId(new CustomerId(createOrderCommand.getCustomerId()))
				.withRestaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.withDeliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
				.withPrice(new Money(createOrderCommand.getPrice()))
				.withItems(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
				.build();
	}

	public CreateOrderResponse orderToCreateOrderResponse(Order order) {
		return CreateOrderResponse.builder()
				.withOrderTrackingId(order.getTrackingId().getValue())
				.withOrderStatus(order.getOrderStatus())
				.build();
	}

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .withOrderTrackingId(order.getTrackingId().getValue())
                .withOrderStatus(order.getOrderStatus())
                .withMessage(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
    	return TrackOrderResponse.builder()
    			.withOrderTrackingId(order.getTrackingId().getValue())
    			.withOrderStatus(order.getOrderStatus())
    			.withFailureMessages(order.getFailureMessages())
    			.build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
			List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
		return orderItems.stream()
				.map(item -> 
					OrderItem.builder()
						.withProduct(new Product(new ProductId(item.getProductId())))
						.withPrice(new Money(item.getPrice()))
						.withQuantity(item.getQuantity())
						.withSubTotal(new Money(item.getSubTotal()))
					.build()).toList();
	}

	private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
		return new StreetAddress(
				UUID.randomUUID(),
				orderAddress.getStreet(),
				orderAddress.getPostalCode(),
				orderAddress.getCity());
	}
}
