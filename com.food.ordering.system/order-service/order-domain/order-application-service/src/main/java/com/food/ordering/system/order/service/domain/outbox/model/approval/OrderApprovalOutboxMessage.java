package com.food.ordering.system.order.service.domain.outbox.model.approval;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import javax.annotation.Generated;

public class OrderApprovalOutboxMessage {

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

	@Generated("SparkTools")
	private OrderApprovalOutboxMessage(Builder builder) {
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

	public void setProcessedAt(ZonedDateTime processedAt) {
		this.processedAt = processedAt;
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
	public SagaStatus getSagaStatus() {
		return sagaStatus;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public OutboxStatus getOutboxStatus() {
		return outboxStatus;
	}
	public int getVersion() {
		return version;
	}
	public void setSagaStatus(SagaStatus sagaStatus) {
		this.sagaStatus = sagaStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setOutboxStatus(OutboxStatus outboxStatus) {
		this.outboxStatus = outboxStatus;
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

		public OrderApprovalOutboxMessage build() {
			return new OrderApprovalOutboxMessage(this);
		}
	}

}
