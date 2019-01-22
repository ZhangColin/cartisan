package com.cartisan.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class GenericResponse<T> {
    @ApiModelProperty(value = "执行状态", required = true)
    private Boolean flag;
    @ApiModelProperty(value = "执行结果编码", required = true , example = "20000")
    private Integer code;
    @ApiModelProperty(value = "提示信息", required = true)
    private String message;
    @ApiModelProperty(value = "返回结果", required = true)
    private T data;

    public GenericResponse() {
    }

    public GenericResponse(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public GenericResponse(Boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
