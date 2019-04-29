package com.cartisan.common.exceptions;

import com.cartisan.common.constants.CodeMessage;

/**
 * @author colin
 */
public class EntityNotFoundException extends CartisanException {
    public EntityNotFoundException(CodeMessage codeMessage) {
        super(codeMessage);
    }
}
