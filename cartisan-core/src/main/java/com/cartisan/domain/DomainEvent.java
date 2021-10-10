package com.cartisan.domain;

/**
 * @author colin
 */
public interface DomainEvent<T> {
    boolean sameEventAs(T other);
}
