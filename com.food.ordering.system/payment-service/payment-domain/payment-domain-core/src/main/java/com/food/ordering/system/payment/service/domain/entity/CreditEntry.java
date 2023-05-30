package com.food.ordering.system.payment.service.domain.entity;

import javax.annotation.processing.Generated;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditEntryId;

public class CreditEntry extends BaseEntity<CreditEntryId> {

	private final CustomerId customerId;
	private Money totalCreditAmount;

	public void addCreditAmount(Money amount) {
		totalCreditAmount = totalCreditAmount.add(amount);
	}

	public void substractAmount(Money amount) {
		totalCreditAmount = totalCreditAmount.substract(amount);
	}

	@Generated("SparkTools")
	private CreditEntry(Builder builder) {
		super.setId(builder.creditEntryId);
		this.customerId = builder.customerId;
		this.totalCreditAmount = builder.totalCreditAmount;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}
	public Money getTotalCreditAmount() {
		return totalCreditAmount;
	}
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private CustomerId customerId;
		private Money totalCreditAmount;
		private CreditEntryId creditEntryId;

		private Builder() {
		}

		public Builder customerId(CustomerId customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder totalCreditAmount(Money totalCreditAmount) {
			this.totalCreditAmount = totalCreditAmount;
			return this;
		}

		public Builder creditEntryId(CreditEntryId id) {
			creditEntryId = id;
			return this;
		}

		public CreditEntry build() {
			return new CreditEntry(this);
		}
	}
}
