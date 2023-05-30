package com.food.ordering.system.payment.service.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;

import javax.annotation.Generated;

import com.food.ordering.system.domain.valueobject.PaymentOrderStatus;

public class PaymentRequest {

    private String id;
    private String sagaId;
    private String orderId;
    private String customerId;
    private BigDecimal price;
    private Instant createdAt;
    private PaymentOrderStatus paymentOrderStatus;

	@Generated("SparkTools")
	private PaymentRequest(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.orderId = builder.orderId;
		this.customerId = builder.customerId;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.paymentOrderStatus = builder.paymentOrderStatus;
	}

    public PaymentRequest(String id, String sagaId, String orderId, String customerId, BigDecimal price,
			Instant createdAt, PaymentOrderStatus paymentOrderStatus) {
		super();
		this.id = id;
		this.sagaId = sagaId;
		this.orderId = orderId;
		this.customerId = customerId;
		this.price = price;
		this.createdAt = createdAt;
		this.paymentOrderStatus = paymentOrderStatus;
	}

	public void setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }

	public String getId() {
		return id;
	}

	public String getSagaId() {
		return sagaId;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public PaymentOrderStatus getPaymentOrderStatus() {
		return paymentOrderStatus;
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
		private String customerId;
		private BigDecimal price;
		private Instant createdAt;
		private PaymentOrderStatus paymentOrderStatus;

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

		public Builder paymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
			this.paymentOrderStatus = paymentOrderStatus;
			return this;
		}

		public PaymentRequest build() {
			return new PaymentRequest(this);
		}
	}
}
