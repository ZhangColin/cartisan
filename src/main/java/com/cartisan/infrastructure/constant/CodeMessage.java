package com.cartisan.infrastructure.constant;

import lombok.Getter;

/**
 * @author zhangcolin
 */
@Getter
public class CodeMessage {
    private final Integer code;
    private final String message;

    protected CodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessage fillArgs(String... args) {
        Integer code = this.code;
        String message = String.format(this.message, (Object[]) args);

        return new CodeMessage(code, message);
    }

    public static final CodeMessage SUCCESS = new CodeMessage(200, "%s");
    public static final CodeMessage FAIL = new CodeMessage(500, "%s");

    public static final CodeMessage BAD_REQUEST = new CodeMessage(400, "Bad Request");
    public static final CodeMessage UNAUTHORIZED = new CodeMessage(401, "需要身份验证。");
    public static final CodeMessage FORBIDDEN = new CodeMessage(403, "没有获得此资源的授权");
    public static final CodeMessage NOT_FOUND = new CodeMessage(404, "Path Not Found: %s");
    public static final CodeMessage METHOD_NOT_ALLOWED = new CodeMessage(405, "Method Not Allowed: %s");
    public static final CodeMessage REQUEST_TIMEOUT = new CodeMessage(408, "Request Timeout");
    public static final CodeMessage INTERNAL_SERVER_ERROR = new CodeMessage(500, "系统内部错误，请与管理员联系。");
    public static final CodeMessage NOT_IMPLEMENTED = new CodeMessage(501, "Not Implemented");
    public static final CodeMessage BAD_GATEWAY = new CodeMessage(502, "Bad Gateway");
    public static final CodeMessage SERVICE_UNAVAILABLE = new CodeMessage(503, "Service Unavailable");
    public static final CodeMessage GATEWAY_TIMEOUT = new CodeMessage(504, "Gateway Timeout");

    public static final CodeMessage UNKNOWN = new CodeMessage(500, "发生未知错误，请与管理员联系。");
    public static final CodeMessage LIMIT_ERROR = new CodeMessage(500, "访问次数受限制。");
    public static final CodeMessage ENTITY_NOT_FOUND = new CodeMessage(404, "实体没有找到。");
    public static final CodeMessage ENTITY_EXIST = new CodeMessage(500, "实体已经存在。");
    public static final CodeMessage VALIDATE_ERROR = new CodeMessage(400, "%s");
}
