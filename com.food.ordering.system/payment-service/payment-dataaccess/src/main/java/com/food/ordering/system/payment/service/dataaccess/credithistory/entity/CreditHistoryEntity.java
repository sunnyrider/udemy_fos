package com.food.ordering.system.payment.service.dataaccess.credithistory.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;
import javax.annotation.Generated;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "credit_history")
@Entity
public class CreditHistoryEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

	@Generated("SparkTools")
	private CreditHistoryEntity(Builder builder) {
		this.id = builder.id;
		this.customerId = builder.customerId;
		this.amount = builder.amount;
		this.type = builder.type;
	}

    public CreditHistoryEntity() {
    }

    public CreditHistoryEntity(UUID id, UUID customerId, BigDecimal amount, TransactionType type) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.amount = amount;
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditHistoryEntity that = (CreditHistoryEntity) o;
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
		private UUID customerId;
		private BigDecimal amount;
		private TransactionType type;

		private Builder() {
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder customerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder amount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public Builder type(TransactionType type) {
			this.type = type;
			return this;
		}

		public CreditHistoryEntity build() {
			return new CreditHistoryEntity(this);
		}
	}
}
