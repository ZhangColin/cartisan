package com.cartisan.common.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author colin
 */
@Getter
@ToString
public class CodeMessage {

    public static CodeMessage SUCCESS = new CodeMessage(200000, "成功");
    public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务端异常");
    public static CodeMessage VALIDATE_ERROR = new CodeMessage(500300, "参数校验异常：%s");

    private Integer code;
    private String message;

    protected CodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessage fillArgs(String... args) {
        Integer code = this.code;
        String message = String.format(this.message, args);

        return new CodeMessage(code, message);
    }
}
