package com.paymentchain.domain.model.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for all domain events.
 *
 * @author benas
 */
public abstract class DomainEvent {

    private final String eventId;
    private final Instant occurredOn;
    private final String eventType;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
        this.eventType = this.getClass().getSimpleName();
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public String getEventType() {
        return eventType;
    }
}
