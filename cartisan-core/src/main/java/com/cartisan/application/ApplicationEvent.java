package com.cartisan.application;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author colin
 */
public class ApplicationEvent implements Event{
    protected final String eventId;
    protected final String createdTimestamp;
    protected final String version;

    public ApplicationEvent() {
        this("v1.0");
    }

    public ApplicationEvent(String version) {
        this.eventId = UUID.randomUUID().toString();
        this.createdTimestamp = LocalDateTime.now().toString();
        this.version = version;
    }

    @Override
    public String eventId() {
        return eventId;
    }
}
