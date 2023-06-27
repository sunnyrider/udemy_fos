package com.food.ordering.system.restaurant.service.domain.outbox.model;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Getter
//@Builder
//@AllArgsConstructor
public class OrderEventPayload {

    @JsonProperty
    private String orderId;

    @JsonProperty
    private String restaurantId;

    @JsonProperty
    private ZonedDateTime createdAt;

    @JsonProperty
    private String orderApprovalStatus;

    @JsonProperty
    private List<String> failureMessages;

	@Generated("SparkTools")
	private OrderEventPayload(Builder builder) {
		this.orderId = builder.orderId;
		this.restaurantId = builder.restaurantId;
		this.createdAt = builder.createdAt;
		this.orderApprovalStatus = builder.orderApprovalStatus;
		this.failureMessages = builder.failureMessages;
	}

	public OrderEventPayload(String orderId, String restaurantId, ZonedDateTime createdAt, String orderApprovalStatus,
			List<String> failureMessages) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.createdAt = createdAt;
		this.orderApprovalStatus = orderApprovalStatus;
		this.failureMessages = failureMessages;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public String getOrderApprovalStatus() {
		return orderApprovalStatus;
	}

	public List<String> getFailureMessages() {
		return failureMessages;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String orderId;
		private String restaurantId;
		private ZonedDateTime createdAt;
		private String orderApprovalStatus;
		private List<String> failureMessages = Collections.emptyList();

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

		public Builder createdAt(ZonedDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder orderApprovalStatus(String orderApprovalStatus) {
			this.orderApprovalStatus = orderApprovalStatus;
			return this;
		}

		public Builder failureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public OrderEventPayload build() {
			return new OrderEventPayload(this);
		}
	}
}
