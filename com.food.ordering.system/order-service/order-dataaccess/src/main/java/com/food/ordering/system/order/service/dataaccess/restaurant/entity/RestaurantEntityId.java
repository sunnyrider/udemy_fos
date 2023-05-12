package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Generated;

public class RestaurantEntityId implements Serializable {
	private static final long serialVersionUID = 5954657376519613783L;

	private UUID restaurantId;
	private UUID orderId;

	@Generated("SparkTools")
	private RestaurantEntityId(Builder builder) {
		this.restaurantId = builder.restaurantId;
		this.orderId = builder.orderId;
	}

	public RestaurantEntityId() {
	}

	public RestaurantEntityId(UUID restaurantId, UUID orderId) {
		this.restaurantId = restaurantId;
		this.orderId = orderId;
	}

	public UUID getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(UUID restaurantId) {
		this.restaurantId = restaurantId;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, restaurantId);
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
		return Objects.equals(orderId, other.orderId) && Objects.equals(restaurantId, other.restaurantId);
	}

	@Override
	public String toString() {
		return "RestaurantEntityId [restaurantId=" + restaurantId + ", orderId=" + orderId + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID restaurantId;
		private UUID orderId;

		private Builder() {
		}

		public Builder RestaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder OrderId(UUID orderId) {
			this.orderId = orderId;
			return this;
		}

		public RestaurantEntityId build() {
			return new RestaurantEntityId(this);
		}
	}
}
