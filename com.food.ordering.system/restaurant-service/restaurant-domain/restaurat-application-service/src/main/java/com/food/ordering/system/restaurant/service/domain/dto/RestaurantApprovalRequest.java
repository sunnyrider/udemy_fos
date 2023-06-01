package com.food.ordering.system.restaurant.service.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import com.food.ordering.system.domain.valueobject.RestaurantOrderStatus;
import com.food.ordering.system.restaurant.service.domain.entity.Product;

//@Getter
//@Builder
//@AllArgsConstructor
public class RestaurantApprovalRequest {
    private String id;
    private String sagaId;
    private String restaurantId;
    private String orderId;
    private RestaurantOrderStatus restaurantOrderStatus;
    private java.util.List<Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

	@Generated("SparkTools")
	private RestaurantApprovalRequest(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.restaurantId = builder.restaurantId;
		this.orderId = builder.orderId;
		this.restaurantOrderStatus = builder.restaurantOrderStatus;
		this.products = builder.products;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
	}

	public String getId() {
		return id;
	}

	public String getSagaId() {
		return sagaId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public String getOrderId() {
		return orderId;
	}

	public RestaurantOrderStatus getRestaurantOrderStatus() {
		return restaurantOrderStatus;
	}

	public java.util.List<Product> getProducts() {
		return products;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public java.time.Instant getCreatedAt() {
		return createdAt;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String id;
		private String sagaId;
		private String restaurantId;
		private String orderId;
		private RestaurantOrderStatus restaurantOrderStatus;
		private java.util.List<Product> products = Collections.emptyList();
		private java.math.BigDecimal price;
		private java.time.Instant createdAt;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder sagaId(String sagaId) {
			this.sagaId = sagaId;
			return this;
		}

		public Builder restaurantId(String restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder restaurantOrderStatus(RestaurantOrderStatus restaurantOrderStatus) {
			this.restaurantOrderStatus = restaurantOrderStatus;
			return this;
		}

		public Builder products(java.util.List<Product> products) {
			this.products = products;
			return this;
		}

		public Builder price(java.math.BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder createdAt(java.time.Instant createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public RestaurantApprovalRequest build() {
			return new RestaurantApprovalRequest(this);
		}
	}

}
