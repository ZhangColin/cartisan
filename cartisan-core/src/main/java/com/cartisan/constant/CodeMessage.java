package com.cartisan.constant;

import lombok.Getter;
import lombok.ToString;

/**
 * @author colin
 */
@Getter
@ToString
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


//    /**操作成功**/
//    RC100(100,"操作成功"),
//    /**操作失败**/
//    RC999(999,"操作失败"),
//    /**服务限流**/
//    RC200(200,"服务开启限流保护,请稍后再试!"),
//    /**服务降级**/
//    RC201(201,"服务开启降级保护,请稍后再试!"),
//    /**热点参数限流**/
//    RC202(202,"热点参数限流,请稍后再试!"),
//    /**系统规则不满足**/
//    RC203(203,"系统规则不满足要求,请稍后再试!"),
//    /**授权规则不通过**/
//    RC204(204,"授权规则不通过,请稍后再试!"),
//    /**access_denied**/
//    RC403(403,"无访问权限,请联系管理员授予权限"),
//    /**access_denied**/
//    RC401(401,"匿名用户访问无权限资源时的异常"),
//    /**服务异常**/
//    RC500(500,"系统异常，请稍后重试"),
//
//    INVALID_TOKEN(2001,"访问令牌不合法"),
//    ACCESS_DENIED(2003,"没有权限访问该资源"),
//    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
//    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
//    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式");




}
