package com.cartisan.constants;

import lombok.Getter;
import lombok.ToString;

/**
 * @author colin
 */
@Getter
@ToString
public class CodeMessage {
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

    public static final CodeMessage SUCCESS = new CodeMessage(200, "%s");
    public static final CodeMessage FAIL = new CodeMessage(500, "%s");

    public static final CodeMessage BAD_REQUEST = new CodeMessage(400, "Bad Request");
    public static final CodeMessage UNAUTHORIZED = new CodeMessage(401, "访问服务需要身份认证，请引导用户到登录页。");
    public static final CodeMessage FORBIDDEN = new CodeMessage(403, "Forbidden");
    public static final CodeMessage NOT_FOUND = new CodeMessage(404, "Not Found");
    public static final CodeMessage METHOD_NOT_ALLOWED = new CodeMessage(405, "Method Not Allowed");
    public static final CodeMessage REQUEST_TIMEOUT = new CodeMessage(408, "Request Timeout");
    public static final CodeMessage INTERNAL_SERVER_ERROR = new CodeMessage(500, "Internal Server Error");
    public static final CodeMessage NOT_IMPLEMENTED = new CodeMessage(501, "Not Implemented");
    public static final CodeMessage BAD_GATEWAY = new CodeMessage(502, "Bad Gateway");
    public static final CodeMessage SERVICE_UNAVAILABLE = new CodeMessage(503, "Service Unavailable");
    public static final CodeMessage GATEWAY_TIMEOUT = new CodeMessage(504, "Gateway Timeout");

    public static final CodeMessage UNKNOWN = new CodeMessage(500, "发生未知异常，请与管理员联系。");
    public static final CodeMessage LIMIT_ERROR = new CodeMessage(500, "访问次数受限制。");
    public static final CodeMessage ENTITY_NOT_FOUND = new CodeMessage(404, "实体没有找到。");
    public static final CodeMessage ENTITY_EXIST = new CodeMessage(500, "实体已经存在。");
    public static final CodeMessage VALIDATE_ERROR = new CodeMessage(500, "%s");
}
