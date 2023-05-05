package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

	private String name;
	private Money price;

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
}
