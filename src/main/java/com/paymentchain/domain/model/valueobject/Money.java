package com.paymentchain.domain.model.valueobject;

import com.paymentchain.domain.exception.DomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value Object representing monetary amount.
 *
 * Immutable and self-validating.
 *
 * @author benas
 */
public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    /**
     * Factory method to create Money instance.
     *
     * @param amount Monetary amount
     * @param currency Currency
     * @return Money instance
     * @throws DomainException if amount is negative
     */
    public static Money of(BigDecimal amount, Currency currency) {
        validate(amount, currency);
        return new Money(amount, currency);
    }

    /**
     * Factory method for zero amount.
     */
    public static Money zero(Currency currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    private static void validate(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new DomainException("Amount cannot be null");
        }
        if (currency == null) {
            throw new DomainException("Currency cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Amount cannot be negative");
        }
    }

    /**
     * Add two money amounts (must be same currency).
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Cannot add different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Subtract money (must be same currency).
     */
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Cannot subtract different currencies");
        }
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Result cannot be negative");
        }
        return new Money(result, this.currency);
    }

    /**
     * Compare amounts (must be same currency).
     */
    public boolean isGreaterThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Cannot compare different currencies");
        }
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isLessThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Cannot compare different currencies");
        }
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    // Getters
    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0 && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
