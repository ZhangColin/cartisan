package com.cartisan.common.exceptions;

import com.cartisan.common.constants.CodeMessage;

/**
 * @author colin
 */
public class EntityAlreadyExistsException extends CartisanException {
    public EntityAlreadyExistsException(CodeMessage codeMessage) {
        super(codeMessage);
    }
}
