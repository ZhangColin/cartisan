package com.cartisan.common;

import com.cartisan.common.constants.CodeMessage;

/**
 * @author colin
 */
class ContextCodeMessage extends CodeMessage {
    protected ContextCodeMessage(Integer code, String message) {
        super(code, message);
    }

    public static CodeMessage APPLICATION_CONTEXT_IS_NULL = new ContextCodeMessage(51001, "applicationContext为空,请检查是否注入springContextHolder");
}
