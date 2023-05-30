package com.food.ordering.system.payment.service.domain.entity;

import javax.annotation.processing.Generated;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valueobject.TransactionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

	private final CustomerId customerId;
	private final Money amount;
	private final TransactionType transactionType;

	@Generated("SparkTools")
	private CreditHistory(Builder builder) {
		this.customerId = builder.customerId;
		this.amount = builder.amount;
		this.transactionType = builder.transactionType;
		super.setId(builder.creditHistoryId);
	}

	public CustomerId getCustomerId() {
		return customerId;
	}
	public Money getAmount() {
		return amount;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	@Generated("SparkTools")
	public static final class Builder {
		private CustomerId customerId;
		private Money amount;
		private TransactionType transactionType;
		private CreditHistoryId creditHistoryId;

		private Builder() {
		}

		public Builder customerId(CustomerId customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder amount(Money amount) {
			this.amount = amount;
			return this;
		}

		public Builder transactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public Builder creditHistoryId(CreditHistoryId id) {
			this.creditHistoryId = id;
			return this;
		}

		public CreditHistory build() {
			return new CreditHistory(this);
		}
	}
}
