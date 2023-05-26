package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

public class OrderItem {

	@NotNull
	private final UUID productId;

	@NotNull
	private final Integer quantity;

	@NotNull
	private final BigDecimal price;

	@NotNull
	private final BigDecimal subTotal;

	@Generated("SparkTools")
	public static final class Builder {
		private UUID productId;
		private Integer quantity;
		private BigDecimal price;
		private BigDecimal subTotal;

		private Builder() {
		}

		public OrderItem build() {
			return new OrderItem(this);
		}

		public Builder withPrice(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder withProductId(UUID productId) {
			this.productId = productId;
			return this;
		}

		public Builder withQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withSubTotal(BigDecimal subTotal) {
			this.subTotal = subTotal;
			return this;
		}
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	private OrderItem(Builder builder) {
		this.productId = builder.productId;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.subTotal = builder.subTotal;
	}

	public OrderItem(@NotNull UUID productId, @NotNull Integer quantity, @NotNull BigDecimal price,
			@NotNull BigDecimal subTotal) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public UUID getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}
}
