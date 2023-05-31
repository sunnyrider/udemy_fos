package com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity;

import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "order_approval", schema = "restaurant")
@Entity
public class OrderApprovalEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private UUID restaurantId;
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus status;

	@Generated("SparkTools")
	private OrderApprovalEntity(Builder builder) {
		this.id = builder.id;
		this.restaurantId = builder.restaurantId;
		this.orderId = builder.orderId;
		this.status = builder.status;
	}

    public OrderApprovalEntity() {
    }

    public OrderApprovalEntity(UUID id, UUID restaurantId, UUID orderId, OrderApprovalStatus status) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.orderId = orderId;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public OrderApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(OrderApprovalStatus status) {
		this.status = status;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private UUID restaurantId;
		private UUID orderId;
		private OrderApprovalStatus status;

		private Builder() {
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder restaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder orderId(UUID orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder status(OrderApprovalStatus status) {
			this.status = status;
			return this;
		}

		public OrderApprovalEntity build() {
			return new OrderApprovalEntity(this);
		}
	}

}
