package com.cartisan.common.domains;

/**
 * @author colin
 */
public interface DomainEvent<T> {
    boolean sameEventAs(T other);
}
