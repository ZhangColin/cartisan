package com.cartisan;

import com.cartisan.constants.CodeMessage;

/**
 * @author colin
 */
class ContextCodeMessage extends CodeMessage {
    protected ContextCodeMessage(Integer code, String message) {
        super(code, message);
    }

    public static CodeMessage APPLICATION_CONTEXT_IS_NULL = new ContextCodeMessage(500, "applicationContext为空,请检查是否注入springContextHolder");
}
