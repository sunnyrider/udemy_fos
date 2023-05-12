package com.food.ordering.system.order.service.dataaccess.order.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderAddressEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderItemEntity;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

@Component
public class OrderDataAccessMapper {

	public OrderEntity orderToOrderEntity(Order order) {
		OrderEntity orderEntity = OrderEntity.builder()
				.Id(order.getId().getValue())
				.CustomerId(order.getCustomerId().getValue())
				.RestaurantId(order.getRestaurantId().getValue())
				.TrackingId(order.getTrackingId().getValue())
				.Address(deliveryAddressToAddressEntity(order.getStreetAddress()))
				.Price(order.getPrice().getAmount())
				.Items(orderItemToOrderItemEntity(order.getItems()))
				.OrderStatus(order.getOrderStatus())
				.FailureMessages(order.getFailureMessages() != null ?
						String.join(Order.FAILURE_MESSAGE_DELIMITER, order.getFailureMessages()) : "")
				.build();

		orderEntity.getAddress().setOrder(orderEntity);
		orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));

		return orderEntity;
	}

	public Order orderEntityToOrder(OrderEntity orderEntity) {
		return Order.builder()
				.withOrderId(new OrderId(orderEntity.getId()))
				.withCustomerId(new CustomerId(orderEntity.getCustomerId()))
				.withRestaurantId(new RestaurantId(orderEntity.getRestaurantId()))
				.withDeliveryAddress(addressEntityToDeliveryAddress(orderEntity.getAddress()))
				.withPrice(new Money(orderEntity.getPrice()))
				.withItems(orderItemEntitiesToOrderItem(orderEntity.getItems()))
				.withTrackingId(new TrackingId(orderEntity.getTrackingId()))
				.withOrderStatus(orderEntity.getOrderStatus())
				.withFailureMessages(orderEntity.getFailureMessages().isEmpty() ?
						new ArrayList<>() :
							new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages()
						.split(Order.FAILURE_MESSAGE_DELIMITER))))
				.build();
	}

	private List<OrderItem> orderItemEntitiesToOrderItem(List<OrderItemEntity> items) {
		return items.stream().map(item -> OrderItem.builder()
				.withOrderItemId(new OrderItemId(item.getId()))
				.withProduct(new Product(new ProductId(item.getProductId())))
				.withPrice(new Money(item.getPrice()))
				.withQuantity(item.getQuantity())
				.withSubTotal(new Money(item.getSubTotal()))
				.build()).toList();
	}

	private StreetAddress addressEntityToDeliveryAddress(OrderAddressEntity address) {
		return new StreetAddress(address.getId(), 
				address.getStreet(), 
				address.getPostalCode(), 
				address.getCity());
	}

	private List<OrderItemEntity> orderItemToOrderItemEntity(List<OrderItem> items) {
		return items.stream()
				.map(item -> OrderItemEntity.builder()
						.Id(item.getId().getValue())
						.ProductId(item.getProduct().getId().getValue())
						.Price(item.getPrice().getAmount())
						.Quantity(item.getQuantity())
						.SubTotal(item.getSubTotal().getAmount())
						.build()).toList();
	}

	private OrderAddressEntity deliveryAddressToAddressEntity(StreetAddress streetAddress) {
		return OrderAddressEntity.builder()
				.Id(streetAddress.getId())
				.Street(streetAddress.getStreet())
				.PostalCode(streetAddress.getPostalCode())
				.City(streetAddress.getCity())
				.build();
	}
}
