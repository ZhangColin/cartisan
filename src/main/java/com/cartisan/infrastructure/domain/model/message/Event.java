package com.cartisan.infrastructure.domain.model.message;

import java.io.Serializable;

/**
 * @author zhangcolin
 */
public interface Event extends Serializable {
    String eventId();
}
