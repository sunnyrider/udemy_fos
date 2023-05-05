package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

	public static final Money ZERO = new Money(BigDecimal.ZERO);

	private final BigDecimal amount;

	public Money(BigDecimal vNewVal) {
		amount = vNewVal;
	}

	public boolean isGreaterThanZero() {
		return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
	}

	public boolean isGreaterthan(Money money) {
		return amount != null && amount.compareTo(money.getAmount()) > 0;
	}

	public Money add(Money money) {
		return new Money(setScale(amount.add(money.getAmount())));
	}

	public Money substract(Money money) {
		return new Money(setScale(amount.subtract(money.getAmount())));
	}

	public Money multipy(int multiplier) {
		return new Money(setScale(amount.multiply(BigDecimal.valueOf(multiplier))));
	}

	private BigDecimal setScale(BigDecimal input) {
		return input.setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return Objects.equals(amount, other.amount);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + "]";
	}
}
