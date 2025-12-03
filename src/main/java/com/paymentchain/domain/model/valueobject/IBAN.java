package com.paymentchain.domain.model.valueobject;

import com.paymentchain.domain.exception.DomainException;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object for International Bank Account Number.
 *
 * Validates Spanish IBAN format.
 *
 * @author benas
 */
public final class IBAN {

    private static final Pattern SPANISH_IBAN_PATTERN = Pattern.compile("ES\\d{22}");

    private final String value;

    private IBAN(String value) {
        this.value = value;
    }

    /**
     * Factory method to create IBAN.
     *
     * @param value IBAN string
     * @return IBAN instance
     * @throws DomainException if invalid format
     */
    public static IBAN of(String value) {
        validate(value);
        return new IBAN(value.toUpperCase().replaceAll("\\s", ""));
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new DomainException("IBAN cannot be null or empty");
        }

        String normalized = value.toUpperCase().replaceAll("\\s", "");

        if (!SPANISH_IBAN_PATTERN.matcher(normalized).matches()) {
            throw new DomainException("Invalid Spanish IBAN format: " + value);
        }
    }

    public String getValue() {
        return value;
    }

    /**
     * Get masked IBAN for display (ES12****5678).
     */
    public String getMasked() {
        if (value.length() < 8) {
            return value;
        }
        return value.substring(0, 4) + "****" + value.substring(value.length() - 4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IBAN iban = (IBAN) o;
        return Objects.equals(value, iban.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
