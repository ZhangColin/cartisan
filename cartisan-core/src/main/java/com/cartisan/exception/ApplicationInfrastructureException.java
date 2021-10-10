package com.cartisan.exception;

/**
 * @author colin
 */
public class ApplicationInfrastructureException extends ApplicationException{
    public ApplicationInfrastructureException(String message, Exception ex) {
        super(message, ex);
    }
}
