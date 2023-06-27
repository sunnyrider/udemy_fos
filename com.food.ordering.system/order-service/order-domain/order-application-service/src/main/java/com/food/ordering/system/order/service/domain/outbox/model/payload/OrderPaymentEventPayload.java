package com.food.ordering.system.order.service.domain.outbox.model.payload;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPaymentEventPayload {

    @JsonProperty
    private String orderId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private String paymentOrderStatus;

	@Generated("SparkTools")
	private OrderPaymentEventPayload(Builder builder) {
		this.orderId = builder.orderId;
		this.customerId = builder.customerId;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.paymentOrderStatus = builder.paymentOrderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public String getPaymentOrderStatus() {
		return paymentOrderStatus;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String orderId;
		private String customerId;
		private BigDecimal price;
		private ZonedDateTime createdAt;
		private String paymentOrderStatus;

		private Builder() {
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder customerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder createdAt(ZonedDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder paymentOrderStatus(String paymentOrderStatus) {
			this.paymentOrderStatus = paymentOrderStatus;
			return this;
		}

		public OrderPaymentEventPayload build() {
			return new OrderPaymentEventPayload(this);
		}
	}
}
