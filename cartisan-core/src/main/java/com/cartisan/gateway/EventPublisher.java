package com.cartisan.gateway;

import com.cartisan.event.Event;

/**
 * @author colin
 */
public interface EventPublisher<T extends Event> {
    void publish(T event);
}
