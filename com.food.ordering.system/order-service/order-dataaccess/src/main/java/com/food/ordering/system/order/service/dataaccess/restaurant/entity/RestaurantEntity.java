package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class RestaurantEntity {

	@Id
	private UUID restaurantId;
	@Id
	private UUID productId;

	private String restaurantName;
	private Boolean restaurantActive;
	private String productName;
	private BigDecimal productPrice;

	@Generated("SparkTools")
	private RestaurantEntity(Builder builder) {
		this.restaurantId = builder.restaurantId;
		this.productId = builder.productId;
		this.restaurantName = builder.restaurantName;
		this.restaurantActive = builder.restaurantActive;
		this.productName = builder.productName;
		this.productPrice = builder.productPrice;
	}

	public RestaurantEntity() {
	}

	public RestaurantEntity(UUID restaurantId, UUID prodId, String restaurantName, Boolean restaurantActive,
			String productName, BigDecimal productPrice) {
		this.restaurantId = restaurantId;
		this.productId = prodId;
		this.restaurantName = restaurantName;
		this.restaurantActive = restaurantActive;
		this.productName = productName;
		this.productPrice = productPrice;
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

	public void setProductId(UUID prodId) {
		this.productId = prodId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Boolean getRestaurantActive() {
		return restaurantActive;
	}

	public void setRestaurantActive(Boolean restaurantActive) {
		this.restaurantActive = restaurantActive;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
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
		RestaurantEntity other = (RestaurantEntity) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(restaurantId, other.restaurantId);
	}

	@Override
	public String toString() {
		return "RestaurantEntity [restaurantId=" + restaurantId + ", productId=" + productId + ", restaurantName="
				+ restaurantName + ", restaurantActive=" + restaurantActive + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID restaurantId;
		private UUID productId;
		private String restaurantName;
		private Boolean restaurantActive;
		private String productName;
		private BigDecimal productPrice;

		private Builder() {
		}

		public Builder RestaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder productId(UUID prodId) {
			this.productId = prodId;
			return this;
		}

		public Builder RestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
			return this;
		}

		public Builder RestaurantActive(Boolean restaurantActive) {
			this.restaurantActive = restaurantActive;
			return this;
		}

		public Builder ProductName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder ProductPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
			return this;
		}

		public RestaurantEntity build() {
			return new RestaurantEntity(this);
		}
	}

}
