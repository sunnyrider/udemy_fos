package com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import javax.annotation.Generated;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "restaurant_approval_outbox")
@Entity
public class ApprovalOutboxEntity {

    @Id
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;
    private String payload;
    @Enumerated(EnumType.STRING)
    private SagaStatus sagaStatus;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private OutboxStatus outboxStatus;
    @Version
    private int version;

	@Generated("SparkTools")
	private ApprovalOutboxEntity(Builder builder) {
		this.id = builder.id;
		this.sagaId = builder.sagaId;
		this.createdAt = builder.createdAt;
		this.processedAt = builder.processedAt;
		this.type = builder.type;
		this.payload = builder.payload;
		this.sagaStatus = builder.sagaStatus;
		this.orderStatus = builder.orderStatus;
		this.outboxStatus = builder.outboxStatus;
		this.version = builder.version;
	}

    public ApprovalOutboxEntity() {
    }

    public ApprovalOutboxEntity(UUID id, UUID sagaId, ZonedDateTime createdAt, ZonedDateTime processedAt, String type,
			String payload, SagaStatus sagaStatus, OrderStatus orderStatus, OutboxStatus outboxStatus, int version) {
		this.id = id;
		this.sagaId = sagaId;
		this.createdAt = createdAt;
		this.processedAt = processedAt;
		this.type = type;
		this.payload = payload;
		this.sagaStatus = sagaStatus;
		this.orderStatus = orderStatus;
		this.outboxStatus = outboxStatus;
		this.version = version;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getSagaId() {
		return sagaId;
	}

	public void setSagaId(UUID sagaId) {
		this.sagaId = sagaId;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(ZonedDateTime processedAt) {
		this.processedAt = processedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public SagaStatus getSagaStatus() {
		return sagaStatus;
	}

	public void setSagaStatus(SagaStatus sagaStatus) {
		this.sagaStatus = sagaStatus;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OutboxStatus getOutboxStatus() {
		return outboxStatus;
	}

	public void setOutboxStatus(OutboxStatus outboxStatus) {
		this.outboxStatus = outboxStatus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalOutboxEntity that = (ApprovalOutboxEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
		private SagaStatus sagaStatus;
		private OrderStatus orderStatus;
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

		public Builder sagaStatus(SagaStatus sagaStatus) {
			this.sagaStatus = sagaStatus;
			return this;
		}

		public Builder orderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
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

		public ApprovalOutboxEntity build() {
			return new ApprovalOutboxEntity(this);
		}
	}
}

