package com.cartisan.exceptions;

import com.cartisan.constants.CodeMessage;

/**
 * @author colin
 */
public class EntityNotFoundException extends CartisanException {
    public EntityNotFoundException(CodeMessage codeMessage) {
        super(codeMessage);
    }
}
