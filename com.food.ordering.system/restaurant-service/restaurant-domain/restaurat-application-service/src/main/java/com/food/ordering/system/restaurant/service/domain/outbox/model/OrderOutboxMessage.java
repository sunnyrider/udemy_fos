package com.food.ordering.system.restaurant.service.domain.outbox.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.outbox.OutboxStatus;
import javax.annotation.Generated;

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
    private OutboxStatus outboxStatus;
    private OrderApprovalStatus approvalStatus;
    private int version;

	@Generated("SparkTools")
	private OrderOutboxMessage(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.createdAt = builder.createdAt;
		this.processedAt = builder.processedAt;
		this.type = builder.type;
		this.payload = builder.payload;
		this.outboxStatus = builder.outboxStatus;
		this.approvalStatus = builder.approvalStatus;
		this.version = builder.version;
	}

    public OrderOutboxMessage(UUID id, UUID sagaId, ZonedDateTime createdAt, ZonedDateTime processedAt, String type,
			String payload, OutboxStatus outboxStatus, OrderApprovalStatus approvalStatus, int version) {
		this.id = id;
		this.sagaId = sagaId;
		this.createdAt = createdAt;
		this.processedAt = processedAt;
		this.type = type;
		this.payload = payload;
		this.outboxStatus = outboxStatus;
		this.approvalStatus = approvalStatus;
		this.version = version;
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

	public OutboxStatus getOutboxStatus() {
		return outboxStatus;
	}

	public OrderApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public int getVersion() {
		return version;
	}

	public void setOutboxStatus(OutboxStatus status) {
        this.outboxStatus = status;
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
		private OutboxStatus outboxStatus;
		private OrderApprovalStatus approvalStatus;
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

		public Builder outboxStatus(OutboxStatus outboxStatus) {
			this.outboxStatus = outboxStatus;
			return this;
		}

		public Builder approvalStatus(OrderApprovalStatus approvalStatus) {
			this.approvalStatus = approvalStatus;
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
