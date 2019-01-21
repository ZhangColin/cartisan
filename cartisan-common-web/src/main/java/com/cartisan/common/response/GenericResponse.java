package com.cartisan.common.response;

import lombok.Data;

/**
 * @author colin
 */
@Data
public class GenericResponse<T> {
    private Boolean flag;
    private Integer code;
    private String message;
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
