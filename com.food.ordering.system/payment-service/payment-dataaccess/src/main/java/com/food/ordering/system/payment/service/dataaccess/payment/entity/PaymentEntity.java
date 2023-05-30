package com.food.ordering.system.payment.service.dataaccess.payment.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import javax.annotation.Generated;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "payments", schema = "payment")
@Entity
public class PaymentEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private UUID customerId;
    private UUID orderId;
    private BigDecimal price;

	@Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private ZonedDateTime createdAt;

    @Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private UUID customerId;
		private UUID orderId;
		private BigDecimal price;
		private PaymentStatus status;
		private ZonedDateTime createdAt;

		private Builder() {
		}

		public PaymentEntity build() {
			return new PaymentEntity(this);
		}

		public Builder createdAt(ZonedDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder customerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder orderId(UUID orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder status(PaymentStatus status) {
			this.status = status;
			return this;
		}
	}

    @Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

    public PaymentEntity() {
    }

	@Generated("SparkTools")
	private PaymentEntity(Builder builder) {
		this.id = builder.id;
		this.customerId = builder.customerId;
		this.orderId = builder.orderId;
		this.price = builder.price;
		this.status = builder.status;
		this.createdAt = builder.createdAt;
	}

	public PaymentEntity(UUID id, UUID customerId, UUID orderId, BigDecimal price, PaymentStatus status,
			ZonedDateTime createdAt) {
		this.id = id;
		this.customerId = customerId;
		this.orderId = orderId;
		this.price = price;
		this.status = status;
		this.createdAt = createdAt;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public UUID getId() {
		return id;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

    public void setId(UUID id) {
		this.id = id;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return id.equals(that.id);
    }

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }

	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + "]";
	}
}
