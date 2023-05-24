package com.food.ordering.system.order.service.domain.dto.create;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class OrderAddress {

	@NotNull
	@Max(value = 50)
	private final String street;

	@NotNull
	@Max(value = 10)
	private final String postalCode;

	@NotNull
	@Max(value = 50)
	private final String city;

	@Generated("SparkTools")
	private OrderAddress(Builder builder) {
		this.street = builder.street;
		this.postalCode = builder.postalCode;
		this.city = builder.city;
	}

	public OrderAddress(@NotNull String street, @NotNull String postalCode, @NotNull String city) {
		super();
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getStreet() {
		return street;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String street;
		private String postalCode;
		private String city;

		private Builder() {
		}

		public Builder withStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder withPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public OrderAddress build() {
			return new OrderAddress(this);
		}
	}	
}
