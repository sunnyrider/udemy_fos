package com.food.ordering.system.order.service.domain.config;

import javax.annotation.Generated;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfigData {

	@Generated("SparkTools")
	public static final class Builder {
		private String paymentRequestTopicName;
		private String paymentResponseTopicName;
		private String restaurantApprovalRequestTopicName;
		private String restaurantApprovalResponseTopicName;

		private Builder() {
		}

		public OrderServiceConfigData build() {
			return new OrderServiceConfigData(this);
		}

		public Builder PaymentRequestTopicName(String paymentRequestTopicName) {
			this.paymentRequestTopicName = paymentRequestTopicName;
			return this;
		}

		public Builder PaymentResponseTopicName(String paymentResponseTopicName) {
			this.paymentResponseTopicName = paymentResponseTopicName;
			return this;
		}

		public Builder RestaurantApprovalRequestTopicName(String restaurantApprovalRequestTopicName) {
			this.restaurantApprovalRequestTopicName = restaurantApprovalRequestTopicName;
			return this;
		}

		public Builder RestaurantApprovalResponseTopicName(String restaurantApprovalResponseTopicName) {
			this.restaurantApprovalResponseTopicName = restaurantApprovalResponseTopicName;
			return this;
		}
	}
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	private String paymentRequestTopicName;
	private String paymentResponseTopicName;

	private String restaurantApprovalRequestTopicName;

	private String restaurantApprovalResponseTopicName;

	public OrderServiceConfigData() {
	}

	@Generated("SparkTools")
	private OrderServiceConfigData(Builder builder) {
		this.paymentRequestTopicName = builder.paymentRequestTopicName;
		this.paymentResponseTopicName = builder.paymentResponseTopicName;
		this.restaurantApprovalRequestTopicName = builder.restaurantApprovalRequestTopicName;
		this.restaurantApprovalResponseTopicName = builder.restaurantApprovalResponseTopicName;
	}
	public OrderServiceConfigData(String paymentRequestTopicName, String paymentResponseTopicName,
			String restaurantApprovalRequestTopicName, String restaurantApprovalResponseTopicName) {
		this.paymentRequestTopicName = paymentRequestTopicName;
		this.paymentResponseTopicName = paymentResponseTopicName;
		this.restaurantApprovalRequestTopicName = restaurantApprovalRequestTopicName;
		this.restaurantApprovalResponseTopicName = restaurantApprovalResponseTopicName;
	}
	public String getPaymentRequestTopicName() {
		return paymentRequestTopicName;
	}
	public String getPaymentResponseTopicName() {
		return paymentResponseTopicName;
	}
	public String getRestaurantApprovalRequestTopicName() {
		return restaurantApprovalRequestTopicName;
	}
	public String getRestaurantApprovalResponseTopicName() {
		return restaurantApprovalResponseTopicName;
	}
	public void setPaymentRequestTopicName(String paymentRequestTopicName) {
		this.paymentRequestTopicName = paymentRequestTopicName;
	}
	public void setPaymentResponseTopicName(String paymentResponseTopicName) {
		this.paymentResponseTopicName = paymentResponseTopicName;
	}
	public void setRestaurantApprovalRequestTopicName(String restaurantApprovalRequestTopicName) {
		this.restaurantApprovalRequestTopicName = restaurantApprovalRequestTopicName;
	}

	public void setRestaurantApprovalResponseTopicName(String restaurantApprovalResponseTopicName) {
		this.restaurantApprovalResponseTopicName = restaurantApprovalResponseTopicName;
	}
	@Override
	public String toString() {
		return "OrderServiceConfigData [paymentRequestTopicName=" + paymentRequestTopicName
				+ ", paymentResponseTopicName=" + paymentResponseTopicName + ", restaurantApprovalRequestTopicName="
				+ restaurantApprovalRequestTopicName + ", restaurantApprovalResponseTopicName="
				+ restaurantApprovalResponseTopicName + "]";
	}

	
}
