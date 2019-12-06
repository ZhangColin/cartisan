package com.cartisan.responses;

import com.cartisan.constants.CodeMessage;
import com.cartisan.constants.CommonCodeMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Getter
public class GenericResponse<T> {
    @ApiModelProperty(value = "执行状态", required = true)
    private Boolean success;
    @ApiModelProperty(value = "执行结果编码", required = true , example = "200000")
    private Integer code;
    @ApiModelProperty(value = "提示信息", required = true)
    private String message;
    @ApiModelProperty(value = "返回结果", required = true)
    private T data;
    @ApiModelProperty(value = "运行时间截", required = true)
    private LocalDateTime timestamp;

    private GenericResponse(Boolean success, CodeMessage codeMessage, T data) {
        this(success, codeMessage);
        this.data = data;
    }

    private GenericResponse(Boolean success, CodeMessage codeMessage) {
        this.success = success;
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public static <T> GenericResponse<T> success(CodeMessage codeMessage, T data) {
        return new GenericResponse<>(true, codeMessage, data);
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(true, CommonCodeMessage.SUCCESS, data);
    }

    public static <T> GenericResponse<T> success() {
        return new GenericResponse<>(true, CommonCodeMessage.SUCCESS);
    }

    public static GenericResponse<?> fail(CodeMessage codeMessage) {
        return new GenericResponse<>(false, codeMessage);
    }
}
