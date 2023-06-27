package com.food.ordering.system.payment.service.domain.outbox.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.util.Collections;

//@Getter
//@Builder
//@AllArgsConstructor
public class OrderEventPayload {
    @JsonProperty
    private String paymentId;

    @JsonProperty
    private String customerId;

    @JsonProperty
    private String orderId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private ZonedDateTime createdAt;

    @JsonProperty
    private String paymentStatus;

    @JsonProperty
    private List<String> failureMessages;

	@Generated("SparkTools")
	private OrderEventPayload(Builder builder) {
		this.paymentId = builder.paymentId;
		this.customerId = builder.customerId;
		this.orderId = builder.orderId;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.paymentStatus = builder.paymentStatus;
		this.failureMessages = builder.failureMessages;
	}

	public OrderEventPayload(String paymentId, String customerId, String orderId, BigDecimal price,
			ZonedDateTime createdAt, String paymentStatus, List<String> failureMessages) {
		this.paymentId = paymentId;
		this.customerId = customerId;
		this.orderId = orderId;
		this.price = price;
		this.createdAt = createdAt;
		this.paymentStatus = paymentStatus;
		this.failureMessages = failureMessages;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public List<String> getFailureMessages() {
		return failureMessages;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String paymentId;
		private String customerId;
		private String orderId;
		private BigDecimal price;
		private ZonedDateTime createdAt;
		private String paymentStatus;
		private List<String> failureMessages = Collections.emptyList();

		private Builder() {
		}

		public Builder paymentId(String paymentId) {
			this.paymentId = paymentId;
			return this;
		}

		public Builder customerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
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

		public Builder paymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
			return this;
		}

		public Builder failureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public OrderEventPayload build() {
			return new OrderEventPayload(this);
		}
	}

}
