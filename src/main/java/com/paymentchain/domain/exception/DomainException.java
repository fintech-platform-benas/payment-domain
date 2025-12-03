package com.paymentchain.domain.exception;

/**
 * Base exception for domain layer violations.
 *
 * @author benas
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
