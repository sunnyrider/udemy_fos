package com.food.ordering.system.dataaccess.restaurant.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Generated;

public class RestaurantEntityId implements Serializable {
	private static final long serialVersionUID = 5954657376519613783L;

	private UUID restaurantId;
	private UUID productId;

	@Generated("SparkTools")
	private RestaurantEntityId(Builder builder) {
		this.restaurantId = builder.restaurantId;
		this.productId = builder.productId;
	}

	public RestaurantEntityId() {
	}

	public RestaurantEntityId(UUID restaurantId, UUID orderId) {
		this.restaurantId = restaurantId;
		this.productId = orderId;
	}

	public UUID getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(UUID restaurantId) {
		this.restaurantId = restaurantId;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID orderId) {
		this.productId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, restaurantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantEntityId other = (RestaurantEntityId) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(restaurantId, other.restaurantId);
	}

	@Override
	public String toString() {
		return "RestaurantEntityId [restaurantId=" + restaurantId + ", productId=" + productId + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID restaurantId;
		private UUID productId;

		private Builder() {
		}

		public Builder RestaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder ProductId(UUID val) {
			this.productId = val;
			return this;
		}

		public RestaurantEntityId build() {
			return new RestaurantEntityId(this);
		}
	}
}
