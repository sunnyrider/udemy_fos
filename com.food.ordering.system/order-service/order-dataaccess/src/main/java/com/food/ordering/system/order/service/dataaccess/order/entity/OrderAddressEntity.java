package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.annotation.Generated;

@Table(name = "order_address")
@Entity
public class OrderAddressEntity {

	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private OrderEntity order;
		private String street;
		private String postalCode;
		private String city;

		private Builder() {
		}

		public OrderAddressEntity build() {
			return new OrderAddressEntity(this);
		}

		public Builder City(String city) {
			this.city = city;
			return this;
		}

		public Builder Id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder Order(OrderEntity order) {
			this.order = order;
			return this;
		}

		public Builder PostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public Builder Street(String street) {
			this.street = street;
			return this;
		}
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Id
	private UUID id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;

	private String street;

	private String postalCode;

	private String city;

	public OrderAddressEntity() {
	}

	@Generated("SparkTools")
	private OrderAddressEntity(Builder builder) {
		this.id = builder.id;
		this.order = builder.order;
		this.street = builder.street;
		this.postalCode = builder.postalCode;
		this.city = builder.city;
	}

	public OrderAddressEntity(UUID id, OrderEntity order, String street, String postalCode, String city) {
		this.id = id;
		this.order = order;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderAddressEntity other = (OrderAddressEntity) obj;
		return Objects.equals(id, other.id);
	}

	public String getCity() {
		return city;
	}

	public UUID getId() {
		return id;
	}

	public OrderEntity getOrder() {
		return order;
	}
	public String getPostalCode() {
		return postalCode;
	}

	public String getStreet() {
		return street;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "OrderAddressEntity [id=" + id + ", order=" + order + ", street=" + street + ", postalCode=" + postalCode
				+ ", city=" + city + "]";
	}
}
