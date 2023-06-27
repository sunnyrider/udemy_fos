package com.food.ordering.system.order.service.domain.outbox.model.approval;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;

public class OrderApprovalEventPayload {
	@JsonProperty
	private String orderId;
	@JsonProperty
	private String restaurantId;
	@JsonProperty
	private BigDecimal price;
	@JsonProperty
	private ZonedDateTime createdAt;
	@JsonProperty
	private String restaurantOrderStatus;
	@JsonProperty
	private List<OrderApprovalEventProduct> products;

	@Generated("SparkTools")
	private OrderApprovalEventPayload(Builder builder) {
		this.orderId = builder.orderId;
		this.restaurantId = builder.restaurantId;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.restaurantOrderStatus = builder.restaurantOrderStatus;
		this.products = builder.products;
	}

	public List<OrderApprovalEventProduct> getProducts() {
		return products;
	}

	public String getOrderId() {
		return orderId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
	public String getRestaurantOrderStatus() {
		return restaurantOrderStatus;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String orderId;
		private String restaurantId;
		private BigDecimal price;
		private ZonedDateTime createdAt;
		private String restaurantOrderStatus;
		private List<OrderApprovalEventProduct> products = Collections.emptyList();

		private Builder() {
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder restaurantId(String restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder createdAt(ZonedDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder restaurantOrderStatus(String restaurantOrderStatus) {
			this.restaurantOrderStatus = restaurantOrderStatus;
			return this;
		}

		public Builder products(List<OrderApprovalEventProduct> products) {
			this.products = products;
			return this;
		}

		public OrderApprovalEventPayload build() {
			return new OrderApprovalEventPayload(this);
		}
	}
}
