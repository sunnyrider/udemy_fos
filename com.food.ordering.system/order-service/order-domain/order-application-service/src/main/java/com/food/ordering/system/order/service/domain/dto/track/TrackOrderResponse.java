package com.food.ordering.system.order.service.domain.dto.track;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

public class TrackOrderResponse {

	@NotNull
	private final UUID orderTrackingId;

	@NotNull
	private final OrderStatus orderStatus;

	@NotNull
	private final List<String> failureMessages;

	@Generated("SparkTools")
	private TrackOrderResponse(Builder builder) {
		this.orderTrackingId = builder.orderTrackingId;
		this.orderStatus = builder.orderStatus;
		this.failureMessages = builder.failureMessages;
	}

	public UUID getOrderTrackingId() {
		return orderTrackingId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public List<String> getFailureMessages() {
		return failureMessages;
	}

	public TrackOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
			@NotNull List<String> failureMessages) {
		super();
		this.orderTrackingId = orderTrackingId;
		this.orderStatus = orderStatus;
		this.failureMessages = failureMessages;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID orderTrackingId;
		private OrderStatus orderStatus;
		private List<String> failureMessages = Collections.emptyList();

		private Builder() {
		}

		public Builder withOrderTrackingId(UUID orderTrackingId) {
			this.orderTrackingId = orderTrackingId;
			return this;
		}

		public Builder withOrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder withFailureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public TrackOrderResponse build() {
			return new TrackOrderResponse(this);
		}
	}
}
