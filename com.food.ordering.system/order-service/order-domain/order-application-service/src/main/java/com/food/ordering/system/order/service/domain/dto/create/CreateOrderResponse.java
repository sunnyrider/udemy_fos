package com.food.ordering.system.order.service.domain.dto.create;

import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.food.ordering.system.domain.valueobject.OrderStatus;

public class CreateOrderResponse {

	@NotNull
	private final UUID orderTrackingId;

	@NotNull
	private final OrderStatus orderStatus;

	@NotNull
	private final String message;

	@Generated("SparkTools")
	private CreateOrderResponse(Builder builder) {
		this.orderTrackingId = builder.orderTrackingId;
		this.orderStatus = builder.orderStatus;
		this.message = builder.message;
	}

	public CreateOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
			@NotNull String message) {
		super();
		this.orderTrackingId = orderTrackingId;
		this.orderStatus = orderStatus;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public UUID getOrderTrackingId() {
		return orderTrackingId;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID orderTrackingId;
		private OrderStatus orderStatus;
		private String message;

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

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public CreateOrderResponse build() {
			return new CreateOrderResponse(this);
		}
	}
}
