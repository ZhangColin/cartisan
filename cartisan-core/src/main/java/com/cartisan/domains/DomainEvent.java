package com.cartisan.domains;

/**
 * @author colin
 */
public interface DomainEvent<T> {
    boolean sameEventAs(T other);
}
