package com.cartisan.constants;

/**
 * @author colin
 */
public final class CommonCodeMessage extends CodeMessage{
    protected CommonCodeMessage(Integer code, String message) {
        super(code, message);
    }

    public static final CodeMessage SUCCESS = new CommonCodeMessage(20000, "成功");
    public static final CodeMessage SERVER_ERROR = new CommonCodeMessage(50000, "服务端异常");
    public static final CodeMessage LIMIT_ERROR = new CommonCodeMessage(50001, "访问次数受限制");

    public static final CodeMessage VALIDATE_ERROR = new CommonCodeMessage(40000, "%s");
}
