package com.cartisan.response;

import com.cartisan.constant.CodeMessage;
import lombok.Getter;

/**
 * @author colin
 */
@Getter
public class ResponseUtil {

    public static final String SUCCESS_MESSAGE = "执行成功。";

    public static GenericResponse success() {
        return successWithMessage(SUCCESS_MESSAGE);
    }


    public static  <T> GenericResponse<T> success(T data) {
        return successWithMessage(SUCCESS_MESSAGE, data);
    }

    public static GenericResponse successWithMessage(String message) {
        final CodeMessage codeMessage = CodeMessage.SUCCESS.fillArgs(message);
        return new GenericResponse(codeMessage.getCode(), codeMessage.getMessage());
    }

    public static  <T> GenericResponse<T> successWithMessage(String message, T data) {
        final CodeMessage codeMessage = CodeMessage.SUCCESS.fillArgs(message);
        return successWithMessage(codeMessage, data);
    }

    public static  <T> GenericResponse<T> successWithMessage(CodeMessage codeMessage, T data) {
        return new GenericResponse<>(codeMessage.getCode(), codeMessage.getMessage(), data);
    }

    public static GenericResponse fail(String message) {
        final CodeMessage codeMessage = CodeMessage.FAIL.fillArgs(message);
        return fail(codeMessage);
    }

    public static GenericResponse fail(CodeMessage codeMessage) {
        return new GenericResponse(codeMessage.getCode(), codeMessage.getMessage());
    }
}
