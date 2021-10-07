package com.cartisan.responses;

import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    PASSWORD_ERROR(1, "密码错误"),
    USER_EXIST(2, "用户已存在"),
    PARAM_ERROR(3, "参数错误"),
    NEED_LOGIN(10, "用户未登录，请先登录");

    private final Integer code;
    private final String description;

    ResponseEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
