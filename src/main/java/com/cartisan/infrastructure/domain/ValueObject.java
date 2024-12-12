package com.cartisan.infrastructure.domain;

import java.io.Serializable;

/**
 * @author zhangcolin
 */
public interface ValueObject<T> extends Serializable {
    boolean sameValueAs(T other);
}
