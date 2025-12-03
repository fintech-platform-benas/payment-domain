package com.paymentchain.domain.model.valueobject;

import com.paymentchain.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        Email email = Email.of("user@example.com");

        assertThat(email.getValue()).isEqualTo("user@example.com");
    }

    @Test
    void shouldConvertToLowerCase() {
        Email email = Email.of("User@Example.COM");

        assertThat(email.getValue()).isEqualTo("user@example.com");
    }

    @Test
    void shouldRejectNullEmail() {
        assertThatThrownBy(() -> Email.of(null))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null or empty");
    }

    @Test
    void shouldRejectEmptyEmail() {
        assertThatThrownBy(() -> Email.of(""))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null or empty");
    }

    @Test
    void shouldRejectBlankEmail() {
        assertThatThrownBy(() -> Email.of("   "))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("null or empty");
    }

    @Test
    void shouldRejectInvalidEmailFormat() {
        assertThatThrownBy(() -> Email.of("invalid.email"))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("Invalid email format");
    }

    @Test
    void shouldRejectEmailWithoutAtSign() {
        assertThatThrownBy(() -> Email.of("userexample.com"))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("Invalid email format");
    }

    @Test
    void shouldRejectEmailWithoutDomain() {
        assertThatThrownBy(() -> Email.of("user@"))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("Invalid email format");
    }

    @Test
    void shouldMaskEmail() {
        Email email = Email.of("john@example.com");

        String masked = email.getMasked();

        assertThat(masked).isEqualTo("j***@example.com");
    }

    @Test
    void shouldHandleShortEmailInMasking() {
        Email email = Email.of("a@b.com");

        String masked = email.getMasked();

        assertThat(masked).isEqualTo("a@b.com");
    }

    @Test
    void shouldBeEqual() {
        Email email1 = Email.of("user@example.com");
        Email email2 = Email.of("USER@EXAMPLE.COM");

        assertThat(email1).isEqualTo(email2);
    }

    @Test
    void shouldHandleToStringCorrectly() {
        Email email = Email.of("user@example.com");

        assertThat(email.toString()).isEqualTo("user@example.com");
    }

    @Test
    void shouldHandleHashCodeCorrectly() {
        Email email1 = Email.of("user@example.com");
        Email email2 = Email.of("user@example.com");

        assertThat(email1.hashCode()).isEqualTo(email2.hashCode());
    }
}
