package com.food.ordering.system.order.service.domain.dto.track;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

public class TrackOrderQuery {

	@NotNull
	private final UUID orderTrackingId;

	@Generated("SparkTools")
	private TrackOrderQuery(Builder builder) {
		this.orderTrackingId = builder.orderTrackingId;
	}

	public TrackOrderQuery(@NotNull UUID orderTrackingId) {
		super();
		this.orderTrackingId = orderTrackingId;
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

		private Builder() {
		}

		public Builder withOrderTrackingId(UUID orderTrackingId) {
			this.orderTrackingId = orderTrackingId;
			return this;
		}

		public TrackOrderQuery build() {
			return new TrackOrderQuery(this);
		}
	}
}
