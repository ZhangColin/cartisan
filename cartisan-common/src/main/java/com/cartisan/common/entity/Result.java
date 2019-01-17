package com.cartisan.common.entity;

import lombok.Data;

/**
 * <p>Title: Result</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class Result<T> {
    private Boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
