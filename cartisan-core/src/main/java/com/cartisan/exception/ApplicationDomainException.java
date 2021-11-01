package com.cartisan.exception;

/**
 * @author colin
 */
public class ApplicationDomainException extends ApplicationException{
    public ApplicationDomainException(String message, Exception ex) {
        super(message, ex);
    }
}
