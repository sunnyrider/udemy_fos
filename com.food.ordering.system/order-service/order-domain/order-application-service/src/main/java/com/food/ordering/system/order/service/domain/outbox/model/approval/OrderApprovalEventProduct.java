package com.food.ordering.system.order.service.domain.outbox.model.approval;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderApprovalEventProduct {
	@JsonProperty
	private String id;
	@JsonProperty
	private Integer quantity;

	@Generated("SparkTools")
	private OrderApprovalEventProduct(Builder builder) {
		this.id = builder.id;
		this.quantity = builder.quantity;
	}

	public String getId() {
		return id;
	}
	public Integer getQuantity() {
		return quantity;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String id;
		private Integer quantity;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderApprovalEventProduct build() {
			return new OrderApprovalEventProduct(this);
		}
	}
}
