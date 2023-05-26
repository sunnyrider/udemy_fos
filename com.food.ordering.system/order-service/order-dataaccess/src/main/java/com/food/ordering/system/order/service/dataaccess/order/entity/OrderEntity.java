package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.food.ordering.system.domain.valueobject.OrderStatus;

@Table(name = "orders")
@Entity
public class OrderEntity {

	@Id
	private UUID id;
	private UUID customerId;
	private UUID restaurantId;
	private UUID trackingId;
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private String failureMessages;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private OrderAddressEntity address;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItemEntity> items;

	@Generated("SparkTools")
	private OrderEntity(Builder builder) {
		this.id = builder.id;
		this.customerId = builder.customerId;
		this.restaurantId = builder.restaurantId;
		this.trackingId = builder.trackingId;
		this.price = builder.price;
		this.orderStatus = builder.orderStatus;
		this.failureMessages = builder.failureMessages;
		this.address = builder.address;
		this.items = builder.items;
	}

	public OrderEntity() {
	}

	public OrderEntity(UUID id, UUID customerId, UUID restaurantId, UUID trackingId, BigDecimal price,
			OrderStatus orderStatus, String failureMessages, OrderAddressEntity address, List<OrderItemEntity> items) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.trackingId = trackingId;
		this.price = price;
		this.orderStatus = orderStatus;
		this.failureMessages = failureMessages;
		this.address = address;
		this.items = items;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		return Objects.equals(id, other.id);
	}

	public OrderAddressEntity getAddress() {
		return address;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public String getFailureMessages() {
		return failureMessages;
	}

	public UUID getId() {
		return id;
	}

	public List<OrderItemEntity> getItems() {
		return items;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public UUID getRestaurantId() {
		return restaurantId;
	}

	public UUID getTrackingId() {
		return trackingId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setAddress(OrderAddressEntity address) {
		this.address = address;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public void setFailureMessages(String failureMessages) {
		this.failureMessages = failureMessages;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setItems(List<OrderItemEntity> items) {
		this.items = items;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setRestaurantId(UUID restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setTrackingId(UUID val) {
		trackingId = val;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", customerId=" + customerId + ", restaurantId=" + restaurantId
				+ ", trackingId=" + trackingId + ", price=" + price + ", orderStatus=" + orderStatus
				+ ", failureMessages=" + failureMessages + ", address=" + address + ", items=" + items + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private UUID customerId;
		private UUID restaurantId;
		private UUID trackingId;
		private BigDecimal price;
		private OrderStatus orderStatus;
		private String failureMessages;
		private OrderAddressEntity address;
		private List<OrderItemEntity> items = Collections.emptyList();

		private Builder() {
		}

		public Builder Id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder CustomerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder RestaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder TrackingId(UUID vNewVal) {
			trackingId = vNewVal;
			return this;
		}

		public Builder Price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder OrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder FailureMessages(String failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public Builder Address(OrderAddressEntity address) {
			this.address = address;
			return this;
		}

		public Builder Items(List<OrderItemEntity> items) {
			this.items = items;
			return this;
		}

		public OrderEntity build() {
			return new OrderEntity(this);
		}
	}
}
