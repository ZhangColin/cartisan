package com.cartisan.common;

import com.cartisan.common.constants.CodeMessage;

/**
 * @author colin
 */
class ContextCodeMessage extends CodeMessage {
    protected ContextCodeMessage(Integer code, String message) {
        super(code, message);
    }

    public static CodeMessage APPLICATION_CONTEXT_IS_NULL = new ContextCodeMessage(51001, "ApplicationContext is null");
}
