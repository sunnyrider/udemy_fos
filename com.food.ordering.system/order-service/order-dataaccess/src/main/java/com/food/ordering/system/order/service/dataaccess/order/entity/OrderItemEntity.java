package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@IdClass(OrderItemEntityId.class)
@Table(name = "order_items")
@Entity
public class OrderItemEntity {

	@Id
	private Long id;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;

	private UUID productId;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal subTotal;

	@Generated("SparkTools")
	private OrderItemEntity(Builder builder) {
		this.id = builder.id;
		this.order = builder.order;
		this.productId = builder.productId;
		this.price = builder.price;
		this.quantity = builder.quantity;
		this.subTotal = builder.subTotal;
	}

	public OrderItemEntity() {
	}

	public OrderItemEntity(Long id, OrderEntity order, UUID productId, BigDecimal price, Integer quantity,
			BigDecimal subTotal) {
		super();
		this.id = id;
		this.order = order;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemEntity other = (OrderItemEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order);
	}

	@Override
	public String toString() {
		return "OrderItemEntity [id=" + id + ", order=" + order + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private OrderEntity order;
		private UUID productId;
		private BigDecimal price;
		private Integer quantity;
		private BigDecimal subTotal;

		private Builder() {
		}

		public Builder Id(Long id) {
			this.id = id;
			return this;
		}

		public Builder Order(OrderEntity order) {
			this.order = order;
			return this;
		}

		public Builder ProductId(UUID productId) {
			this.productId = productId;
			return this;
		}

		public Builder Price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder Quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder SubTotal(BigDecimal subTotal) {
			this.subTotal = subTotal;
			return this;
		}

		public OrderItemEntity build() {
			return new OrderItemEntity(this);
		}
	}
}
