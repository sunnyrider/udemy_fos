package com.food.ordering.system.order.service.domain.entity;

import javax.annotation.processing.Generated;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

	private String name;
	private Money price;

	@Generated("SparkTools")
	private Product(Builder builder) {
		super.setId(builder.productId);
		this.name = builder.name;
		this.price = builder.price;
	}

	public Product(ProductId id) {
		super.setId(id);
	}

	public Product(ProductId id, String nm, Money prc) {
		super.setId(id);
		name = nm;
		price = prc;
	}

	public void updateWithConfirmedNameAndPrice(String name2, Money price2) {
		name = name2;
		price = price2;
	}

	public String getName() {
		return name;
	}

	public Money getPrice() {
		return price;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private ProductId productId;
		private String name;
		private Money price;

		private Builder() {
		}

		public Builder productId(ProductId id) {
			productId = id;
			return this;
		}

		public Builder Name(String name) {
			this.name = name;
			return this;
		}

		public Builder Price(Money price) {
			this.price = price;
			return this;
		}

		public Product build() {
			return new Product(this);
		}
	}
}
