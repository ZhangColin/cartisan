package com.cartisan.common.response;

/**
 * @author colin
 */
public class ResponseUtils {
    public static GenericResponse success() {
        return new GenericResponse(true, StatusCode.OK, "成功");
    }

    public static GenericResponse success(String message) {
        return new GenericResponse(true, StatusCode.OK, message);
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse(true, StatusCode.OK, "成功", data);
    }

    public static <T> GenericResponse<T> success(String message, T data) {
        return new GenericResponse(true, StatusCode.OK, message, data);
    }

    public static GenericResponse fail() {
        return new GenericResponse(false, StatusCode.ERROR, "失败");
    }

    public static GenericResponse fail(String message) {
        return new GenericResponse(false, StatusCode.ERROR, message);
    }
}
