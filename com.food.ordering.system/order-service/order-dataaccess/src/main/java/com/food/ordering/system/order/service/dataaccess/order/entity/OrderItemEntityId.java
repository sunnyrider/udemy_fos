package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;

public class OrderItemEntityId implements Serializable {

	private static final long serialVersionUID = -5020111959907446873L;

	private Long id;
	private OrderEntity order;

	@Generated("SparkTools")
	private OrderItemEntityId(Builder builder) {
		this.id = builder.id;
		this.order = builder.order;
	}

	public OrderItemEntityId() {
	}

	public OrderItemEntityId(Long id, OrderEntity order) {
		super();
		this.id = id;
		this.order = order;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemEntityId other = (OrderItemEntityId) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order);
	}

	public Long getId() {
		return id;
	}

	public OrderEntity getOrder() {
		return order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order);
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItemEntityId [id=" + id + ", order=" + order + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private OrderEntity order;

		private Builder() {
		}

		public Builder Id(Long id) {
			this.id = id;
			return this;
		}

		public Builder Order(OrderEntity order) {
			this.order = order;
			return this;
		}

		public OrderItemEntityId build() {
			return new OrderItemEntityId(this);
		}
	}

	
}
