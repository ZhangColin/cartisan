package com.cartisan.common.interceptors;

import com.cartisan.common.responses.CodeMessage;

/**
 * @author colin
 */
public class JwtCodeMessage extends CodeMessage {
    public static CodeMessage SIGNATURE_ERROR = new JwtCodeMessage(500900, "签名认证失败");

    protected JwtCodeMessage(Integer code, String message) {
        super(code, message);
    }
}
