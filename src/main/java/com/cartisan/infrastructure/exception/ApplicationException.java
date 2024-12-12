package com.cartisan.infrastructure.exception;

/**
 * @author zhangcolin
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, DomainException ex) {
        super(message, ex);
    }
}
