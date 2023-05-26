package com.food.ordering.system.order.service.dataaccess.customer.entity;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "customers", schema = "customer")
@Entity
public class CustomerEntity {

	@Id
	private UUID id;

	@Generated("SparkTools")
	private CustomerEntity(Builder builder) {
		this.id = builder.id;
	}

	public CustomerEntity() {
	}

	public CustomerEntity(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + "]";
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;

		private Builder() {
		}

		public Builder Id(UUID id) {
			this.id = id;
			return this;
		}

		public CustomerEntity build() {
			return new CustomerEntity(this);
		}
	}
}
