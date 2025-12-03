package com.paymentchain.domain.model.valueobject;

import com.paymentchain.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IBANTest {

    @Test
    void shouldCreateValidSpanishIBAN() {
        IBAN iban = IBAN.of("ES1234567890123456789012");

        assertThat(iban.getValue()).isEqualTo("ES1234567890123456789012");
    }

    @Test
    void shouldNormalizeIBANWithSpaces() {
        IBAN iban = IBAN.of("ES12 3456 7890 1234 5678 9012");

        assertThat(iban.getValue()).isEqualTo("ES1234567890123456789012");
    }

    @Test
    void shouldConvertToUpperCase() {
        IBAN iban = IBAN.of("es1234567890123456789012");

        assertThat(iban.getValue()).startsWith("ES");
    }

    @Test
    void shouldRejectInvalidFormat() {
        assertThatThrownBy(() -> IBAN.of("ES123"))  // Muy corto
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("Invalid");
    }

    @Test
    void shouldRejectNullIBAN() {
        assertThatThrownBy(() -> IBAN.of(null))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null");
    }

    @Test
    void shouldRejectNonSpanishIBAN() {
        assertThatThrownBy(() -> IBAN.of("FR1234567890123456789012"))
            .isInstanceOf(DomainException.class);
    }

    @Test
    void shouldMaskIBAN() {
        IBAN iban = IBAN.of("ES1234567890123456789012");

        String masked = iban.getMasked();

        assertThat(masked).isEqualTo("ES12****9012");
    }

    @Test
    void shouldBeEqual() {
        IBAN iban1 = IBAN.of("ES1234567890123456789012");
        IBAN iban2 = IBAN.of("ES12 3456 7890 1234 5678 9012");

        assertThat(iban1).isEqualTo(iban2);
    }

    @Test
    void shouldRejectEmptyIBAN() {
        assertThatThrownBy(() -> IBAN.of(""))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null or empty");
    }

    @Test
    void shouldRejectBlankIBAN() {
        assertThatThrownBy(() -> IBAN.of("   "))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null or empty");
    }

    @Test
    void shouldHandleToStringCorrectly() {
        IBAN iban = IBAN.of("ES1234567890123456789012");

        assertThat(iban.toString()).isEqualTo("ES1234567890123456789012");
    }

    @Test
    void shouldHandleHashCodeCorrectly() {
        IBAN iban1 = IBAN.of("ES1234567890123456789012");
        IBAN iban2 = IBAN.of("ES1234567890123456789012");

        assertThat(iban1.hashCode()).isEqualTo(iban2.hashCode());
    }
}
