package com.cartisan.response;

import com.cartisan.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author colin
 */
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GenericResponse {
    @ApiModelProperty(value = "错误编码", required = true)
    private Integer status;

    @ApiModelProperty(value = "提示信息", required = true)
    private String message;

    @ApiModelProperty(value = "运行时间截", required = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateUtil.Pattern.DATETIME_SSS)
    private LocalDateTime timestamp;

    public GenericResponse(Integer status, String message) {
        this.status = status;
        this.message = message;

        this.timestamp = LocalDateTime.now();
    }
}
