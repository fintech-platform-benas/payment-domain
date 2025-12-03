package com.paymentchain.domain.exception;

/**
 * Exception for business rule violations.
 *
 * @author benas
 */
public class BusinessRuleException extends DomainException {

    private final String ruleViolated;

    public BusinessRuleException(String ruleViolated, String message) {
        super(message);
        this.ruleViolated = ruleViolated;
    }

    public String getRuleViolated() {
        return ruleViolated;
    }
}
