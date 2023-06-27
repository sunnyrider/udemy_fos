package com.food.ordering.system.order.service.domain.dto.message;

import javax.annotation.Generated;

public class CustomerModel {
    private String id;
    private String username;
    private String firstName;
    private String lastName;

	@Generated("SparkTools")
	private CustomerModel(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
	}

    public CustomerModel(String id, String username, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String id;
		private String username;
		private String firstName;
		private String lastName;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CustomerModel build() {
			return new CustomerModel(this);
		}
	}
}
