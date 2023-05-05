package com.food.ordering.system.order.service.domain.entity;

import javax.annotation.processing.Generated;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

	private OrderId orderId;
	private final Product product;
	private final int quantity;
	private final Money price;
	private final Money subTotal;

	void initializeOrderItem(OrderId oId, OrderItemId itemId) {
		orderId = oId;
		super.setId(itemId);
	}

	boolean isPriceValid() {
		return price.isGreaterThanZero() &&
				price.equals(product.getPrice()) &&
				price.multipy(quantity).equals(subTotal);
	}

	@Generated("SparkTools")
	private OrderItem(Builder builder) {
		super.setId(builder.orderItemId);
		this.product = builder.product;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.subTotal = builder.subTotal;
	}

	public OrderId getOrderId() {
		return orderId;
	}
	public void setOrderId(OrderId orderId) {
		this.orderId = orderId;
	}
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
	public Money getPrice() {
		return price;
	}
	public Money getSubTotal() {
		return subTotal;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private OrderItemId orderItemId;
		private Product product;
		private int quantity;
		private Money price;
		private Money subTotal;

		private Builder() {
		}

		public Builder withOrderItemId(OrderItemId id) {
			orderItemId = id;
			return this;
		}

		public Builder withProduct(Product product) {
			this.product = product;
			return this;
		}

		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withPrice(Money price) {
			this.price = price;
			return this;
		}

		public Builder withSubTotal(Money subTotal) {
			this.subTotal = subTotal;
			return this;
		}

		public OrderItem build() {
			return new OrderItem(this);
		}
	}

}
