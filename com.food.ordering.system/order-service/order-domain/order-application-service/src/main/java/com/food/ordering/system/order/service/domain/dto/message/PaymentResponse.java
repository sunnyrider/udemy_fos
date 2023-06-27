package com.food.ordering.system.order.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import com.food.ordering.system.domain.valueobject.PaymentStatus;

public class PaymentResponse {

	private String id;
	private String sagaId;
	private String orderId;
	private String paymentId;
	private String customerId;
	private BigDecimal price;
	private Instant createdAt;
	private PaymentStatus paymentStatus;
	private List<String> failureMessages;

	@Generated("SparkTools")
	private PaymentResponse(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.orderId = builder.orderId;
		this.paymentId = builder.paymentId;
		this.customerId = builder.customerId;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.paymentStatus = builder.paymentStatus;
		this.failureMessages = builder.failureMessages;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
	public String getCustomerId() {
		return customerId;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	public String getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getSagaId() {
		return sagaId;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	@Generated("SparkTools")
	public static final class Builder {
		private String id;
		private String sagaId;
		private String orderId;
		private String paymentId;
		private String customerId;
		private BigDecimal price;
		private Instant createdAt;
		private PaymentStatus paymentStatus;
		private List<String> failureMessages = Collections.emptyList();

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder sagaId(String sagaId) {
			this.sagaId = sagaId;
			return this;
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder paymentId(String paymentId) {
			this.paymentId = paymentId;
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

		public Builder createdAt(Instant createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder paymentStatus(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
			return this;
		}

		public Builder failureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public PaymentResponse build() {
			return new PaymentResponse(this);
		}
	}
	
}
