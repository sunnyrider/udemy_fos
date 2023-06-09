package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

public class CreateOrderCommand {

	@NotNull
	private final UUID customerId;

	@NotNull
	private final UUID restaurantId;

	@NotNull
	private final BigDecimal price;

	@NotNull
	private final List<OrderItem> items;

	@NotNull
	private final OrderAddress address;

	@Generated("SparkTools")
	private CreateOrderCommand(Builder builder) {
		this.customerId = builder.customerId;
		this.restaurantId = builder.restaurantId;
		this.price = builder.price;
		this.items = builder.items;
		this.address = builder.address;
	}

	public OrderAddress getAddress() {
		return address;
	}
	public UUID getCustomerId() {
		return customerId;
	}
	public List<OrderItem> getItems() {
		return items;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public UUID getRestaurantId() {
		return restaurantId;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID customerId;
		private UUID restaurantId;
		private BigDecimal price;
		private List<OrderItem> items = Collections.emptyList();
		private OrderAddress address;

		private Builder() {
		}

		public Builder customerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder restaurantId(UUID restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder items(List<OrderItem> items) {
			this.items = items;
			return this;
		}

		public Builder address(OrderAddress address) {
			this.address = address;
			return this;
		}

		public CreateOrderCommand build() {
			return new CreateOrderCommand(this);
		}
	}
}
