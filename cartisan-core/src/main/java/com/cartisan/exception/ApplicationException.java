package com.cartisan.exception;

/**
 * @author colin
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Exception ex) {
        super(message, ex);
    }
}
