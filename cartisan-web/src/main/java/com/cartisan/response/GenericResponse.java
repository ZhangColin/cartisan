package com.cartisan.response;

import com.cartisan.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    @ApiModelProperty(value = "执行编码", required = true)
    private final Integer code;

    @ApiModelProperty(value = "提示信息", required = true)
    private final String message;

    @ApiModelProperty(value = "运行时间截", required = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateUtil.Pattern.DATETIME_SSS)
    private final LocalDateTime timestamp;

    @ApiModelProperty(value = "返回结果", required = true)
    @Nullable
    private T data;

    public GenericResponse(Integer code, String message, @Nullable T data) {
        this.code = code;
        this.message = message;
        this.data = data;

        this.timestamp = LocalDateTime.now();
    }

    public GenericResponse(Integer code, String message) {
        this.code = code;
        this.message = message;

        this.timestamp = LocalDateTime.now();
    }
}
