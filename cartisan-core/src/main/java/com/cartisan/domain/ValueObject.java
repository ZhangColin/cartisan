package com.cartisan.domain;

import java.io.Serializable;

/**
 * @author colin
 */
public interface ValueObject<T> extends Serializable {
    boolean sameValueAs(T other);
}
