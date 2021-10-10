package com.cartisan.exception;

/**
 * @author colin
 */
public class ApplicationValidationException extends ApplicationException{
    public ApplicationValidationException(String message, Exception ex) {
        super(message, ex);
    }
}
