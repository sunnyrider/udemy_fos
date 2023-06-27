package com.food.ordering.system.payment.service.domain.outbox.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.annotation.Generated;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.outbox.OutboxStatus;

//@Getter
//@Builder
//@AllArgsConstructor
public class OrderOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;
    private String payload;
    private PaymentStatus paymentStatus;
    private OutboxStatus outboxStatus;
    private int version;

	@Generated("SparkTools")
	private OrderOutboxMessage(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.createdAt = builder.createdAt;
		this.processedAt = builder.processedAt;
		this.type = builder.type;
		this.payload = builder.payload;
		this.paymentStatus = builder.paymentStatus;
		this.outboxStatus = builder.outboxStatus;
		this.version = builder.version;
	}

    public OrderOutboxMessage(UUID id, UUID sagaId, ZonedDateTime createdAt, ZonedDateTime processedAt, String type,
			String payload, PaymentStatus paymentStatus, OutboxStatus outboxStatus, int version) {
		super();
		this.id = id;
		this.sagaId = sagaId;
		this.createdAt = createdAt;
		this.processedAt = processedAt;
		this.type = type;
		this.payload = payload;
		this.paymentStatus = paymentStatus;
		this.outboxStatus = outboxStatus;
		this.version = version;
	}

	public void setOutboxStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
    }

	public UUID getId() {
		return id;
	}

	public UUID getSagaId() {
		return sagaId;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public ZonedDateTime getProcessedAt() {
		return processedAt;
	}

	public String getType() {
		return type;
	}

	public String getPayload() {
		return payload;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public OutboxStatus getOutboxStatus() {
		return outboxStatus;
	}

	public int getVersion() {
		return version;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private UUID sagaId;
		private ZonedDateTime createdAt;
		private ZonedDateTime processedAt;
		private String type;
		private String payload;
		private PaymentStatus paymentStatus;
		private OutboxStatus outboxStatus;
		private int version;

		private Builder() {
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder sagaId(UUID sagaId) {
			this.sagaId = sagaId;
			return this;
		}

		public Builder createdAt(ZonedDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder processedAt(ZonedDateTime processedAt) {
			this.processedAt = processedAt;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder payload(String payload) {
			this.payload = payload;
			return this;
		}

		public Builder paymentStatus(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
			return this;
		}

		public Builder outboxStatus(OutboxStatus outboxStatus) {
			this.outboxStatus = outboxStatus;
			return this;
		}

		public Builder version(int version) {
			this.version = version;
			return this;
		}

		public OrderOutboxMessage build() {
			return new OrderOutboxMessage(this);
		}
	}

}
