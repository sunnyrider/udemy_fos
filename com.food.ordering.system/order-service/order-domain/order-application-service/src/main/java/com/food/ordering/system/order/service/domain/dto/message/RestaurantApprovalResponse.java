package com.food.ordering.system.order.service.domain.dto.message;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

public class RestaurantApprovalResponse {

	private String id;
	private String sagaId;
	private String orderId;
	private String restaurantId;
	private Instant createdAt;
	private OrderApprovalStatus orderApprovalStatus;
	private List<String> failureMessages;

	@Generated("SparkTools")
	private RestaurantApprovalResponse(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.orderId = builder.orderId;
		this.restaurantId = builder.restaurantId;
		this.createdAt = builder.createdAt;
		this.orderApprovalStatus = builder.orderApprovalStatus;
		this.failureMessages = builder.failureMessages;
	}

	public RestaurantApprovalResponse(String id, String sagaId, String orderId, String restaurantId, Instant createdAt,
			OrderApprovalStatus orderApprovalStatus, List<String> failureMessages) {
		super();
		this.id = id;
		this.sagaId = sagaId;
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.createdAt = createdAt;
		this.orderApprovalStatus = orderApprovalStatus;
		this.failureMessages = failureMessages;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	public String getId() {
		return id;
	}
	public OrderApprovalStatus getOrderApprovalStatus() {
		return orderApprovalStatus;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public String getSagaId() {
		return sagaId;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	@Generated("SparkTools")
	public static final class Builder {
		private String id;
		private String sagaId;
		private String orderId;
		private String restaurantId;
		private Instant createdAt;
		private OrderApprovalStatus orderApprovalStatus;
		private List<String> failureMessages = Collections.emptyList();

		private Builder() {
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withSagaId(String sagaId) {
			this.sagaId = sagaId;
			return this;
		}

		public Builder withOrderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder withRestaurantId(String restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder withCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withOrderApprovalStatus(OrderApprovalStatus orderApprovalStatus) {
			this.orderApprovalStatus = orderApprovalStatus;
			return this;
		}

		public Builder withFailureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public RestaurantApprovalResponse build() {
			return new RestaurantApprovalResponse(this);
		}
	}	
}
