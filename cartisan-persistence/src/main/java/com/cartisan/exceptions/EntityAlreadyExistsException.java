package com.cartisan.exceptions;

import com.cartisan.constants.CodeMessage;

/**
 * @author colin
 */
public class EntityAlreadyExistsException extends CartisanException {
    public EntityAlreadyExistsException(CodeMessage codeMessage) {
        super(codeMessage);
    }
}
