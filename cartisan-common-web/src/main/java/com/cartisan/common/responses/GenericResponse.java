package com.cartisan.common.responses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author colin
 */
@Getter
public class GenericResponse<T> {
    @ApiModelProperty(value = "执行状态", required = true)
    private Boolean flag;
    @ApiModelProperty(value = "执行结果编码", required = true , example = "200000")
    private Integer code;
    @ApiModelProperty(value = "提示信息", required = true)
    private String message;
    @ApiModelProperty(value = "返回结果", required = true)
    private T data;

    private GenericResponse(Boolean flag, CodeMessage codeMessage, T data) {
        this(flag, codeMessage);
        this.data = data;
    }

    private GenericResponse(Boolean flag, CodeMessage codeMessage) {
        this.flag = flag;
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

    public static <T> GenericResponse<T> success(CodeMessage codeMessage, T data) {
        return new GenericResponse(true, codeMessage, data);
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse(true, CodeMessage.SUCCESS, data);
    }

    public static <T> GenericResponse<T> success() {
        return new GenericResponse(true, CodeMessage.SUCCESS);
    }

    public static GenericResponse fail(CodeMessage codeMessage) {
        return new GenericResponse(false, codeMessage);
    }
}
