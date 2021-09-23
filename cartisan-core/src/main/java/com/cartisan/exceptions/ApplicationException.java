package com.cartisan.exceptions;

/**
 * @author colin
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message, DomainException ex) {
        super(message, ex);
    }
}
