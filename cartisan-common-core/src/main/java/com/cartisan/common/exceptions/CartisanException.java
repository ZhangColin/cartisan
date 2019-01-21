package com.cartisan.common.exceptions;

/**
 * @author colin
 */
public class CartisanException extends RuntimeException {
    public CartisanException() {
    }

    public CartisanException(String message) {
        super(message);
    }

    public CartisanException(String message, Throwable cause) {
        super(message, cause);
    }
}
