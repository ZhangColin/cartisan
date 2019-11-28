package com.cartisan.constants;

/**
 * @author colin
 */
public final class CommonCodeMessage extends CodeMessage{
    protected CommonCodeMessage(Integer code, String message) {
        super(code, message);
    }

    public static CodeMessage SUCCESS = new CommonCodeMessage(20000, "成功");
    public static CodeMessage SERVER_ERROR = new CommonCodeMessage(50000, "服务端异常");

    public static CodeMessage VALIDATE_ERROR = new CommonCodeMessage(40000, "%s");
}
