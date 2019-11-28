package com.cartisan.interceptors;

import com.cartisan.constants.CodeMessage;

/**
 * @author colin
 */
public class JwtCodeMessage extends CodeMessage {
    public static CodeMessage SIGNATURE_ERROR = new JwtCodeMessage(500900, "签名认证失败");

    protected JwtCodeMessage(Integer code, String message) {
        super(code, message);
    }
}
