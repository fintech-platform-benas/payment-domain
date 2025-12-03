package com.paymentchain.domain.model.valueobject;

import com.paymentchain.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for Money value object.
 */
class MoneyTest {

    @Test
    void shouldCreateValidMoney() {
        Money money = Money.of(new BigDecimal("100.50"), Currency.EUR);

        assertThat(money.getAmount()).isEqualByComparingTo(new BigDecimal("100.50"));
        assertThat(money.getCurrency()).isEqualTo(Currency.EUR);
    }

    @Test
    void shouldRejectNegativeAmount() {
        assertThatThrownBy(() ->
            Money.of(new BigDecimal("-100"), Currency.EUR)
        )
        .isInstanceOf(DomainException.class)
        .hasMessageContaining("negative");
    }

    @Test
    void shouldRejectNullAmount() {
        assertThatThrownBy(() ->
            Money.of(null, Currency.EUR)
        )
        .isInstanceOf(DomainException.class)
        .hasMessageContaining("null");
    }

    @Test
    void shouldAddMoneyOfSameCurrency() {
        Money money1 = Money.of(new BigDecimal("100"), Currency.EUR);
        Money money2 = Money.of(new BigDecimal("50"), Currency.EUR);

        Money result = money1.add(money2);

        assertThat(result.getAmount()).isEqualByComparingTo(new BigDecimal("150.00"));
    }

    @Test
    void shouldRejectAddingDifferentCurrencies() {
        Money eur = Money.of(new BigDecimal("100"), Currency.EUR);
        Money usd = Money.of(new BigDecimal("100"), Currency.USD);

        assertThatThrownBy(() -> eur.add(usd))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("different currencies");
    }

    @Test
    void shouldCompareAmounts() {
        Money greater = Money.of(new BigDecimal("100"), Currency.EUR);
        Money lesser = Money.of(new BigDecimal("50"), Currency.EUR);

        assertThat(greater.isGreaterThan(lesser)).isTrue();
        assertThat(lesser.isLessThan(greater)).isTrue();
    }

    @Test
    void shouldDetectZeroAmount() {
        Money zero = Money.zero(Currency.EUR);

        assertThat(zero.isZero()).isTrue();
    }

    @Test
    void shouldBeImmutable() {
        Money original = Money.of(new BigDecimal("100"), Currency.EUR);
        Money added = original.add(Money.of(new BigDecimal("50"), Currency.EUR));

        // Original no cambia
        assertThat(original.getAmount()).isEqualByComparingTo(new BigDecimal("100"));
        // Nuevo objeto con resultado
        assertThat(added.getAmount()).isEqualByComparingTo(new BigDecimal("150"));
    }

    @Test
    void shouldSubtractMoneyOfSameCurrency() {
        Money money1 = Money.of(new BigDecimal("100"), Currency.EUR);
        Money money2 = Money.of(new BigDecimal("30"), Currency.EUR);

        Money result = money1.subtract(money2);

        assertThat(result.getAmount()).isEqualByComparingTo(new BigDecimal("70.00"));
    }

    @Test
    void shouldRejectSubtractingResultingInNegative() {
        Money money1 = Money.of(new BigDecimal("50"), Currency.EUR);
        Money money2 = Money.of(new BigDecimal("100"), Currency.EUR);

        assertThatThrownBy(() -> money1.subtract(money2))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("negative");
    }

    @Test
    void shouldRejectSubtractingDifferentCurrencies() {
        Money eur = Money.of(new BigDecimal("100"), Currency.EUR);
        Money usd = Money.of(new BigDecimal("50"), Currency.USD);

        assertThatThrownBy(() -> eur.subtract(usd))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("different currencies");
    }

    @Test
    void shouldRoundToTwoDecimalPlaces() {
        Money money = Money.of(new BigDecimal("100.999"), Currency.EUR);

        assertThat(money.getAmount()).isEqualByComparingTo(new BigDecimal("101.00"));
    }

    @Test
    void shouldHandleEqualityCorrectly() {
        Money money1 = Money.of(new BigDecimal("100.00"), Currency.EUR);
        Money money2 = Money.of(new BigDecimal("100.00"), Currency.EUR);
        Money money3 = Money.of(new BigDecimal("100.00"), Currency.USD);

        assertThat(money1).isEqualTo(money2);
        assertThat(money1).isNotEqualTo(money3);
    }

    @Test
    void shouldProduceCorrectToString() {
        Money money = Money.of(new BigDecimal("100.50"), Currency.EUR);

        assertThat(money.toString()).contains("100.50");
        assertThat(money.toString()).contains("EUR");
    }
}
