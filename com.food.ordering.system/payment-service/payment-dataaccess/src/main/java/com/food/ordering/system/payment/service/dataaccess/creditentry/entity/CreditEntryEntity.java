package com.food.ordering.system.payment.service.dataaccess.creditentry.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "credit_entry")
@Entity
public class CreditEntryEntity {
	@Id
    private UUID id;
    private UUID customerId;
    private BigDecimal totalCreditAmount;

	@Generated("SparkTools")
	public static final class Builder {
		private UUID customerId;
		private UUID id;
		private BigDecimal totalCreditAmount;

		private Builder() {
		}

		public CreditEntryEntity build() {
			return new CreditEntryEntity(this);
		}

		public Builder customerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder totalCreditAmount(BigDecimal totalCreditAmount) {
			this.totalCreditAmount = totalCreditAmount;
			return this;
		}
	}
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	public CreditEntryEntity() {
    }

	@Generated("SparkTools")
	private CreditEntryEntity(Builder builder) {
		this.id = builder.id;
		this.customerId = builder.customerId;
		this.totalCreditAmount = builder.totalCreditAmount;
	}

	public CreditEntryEntity(UUID id, UUID customerId, BigDecimal totalCreditAmount) {
		this.id = id;
		this.customerId = customerId;
		this.totalCreditAmount = totalCreditAmount;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditEntryEntity that = (CreditEntryEntity) o;
        return id.equals(that.id);
    }

    public UUID getCustomerId() {
		return customerId;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

    public void setId(UUID id) {
		this.id = id;
	}

	public void setTotalCreditAmount(BigDecimal totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
