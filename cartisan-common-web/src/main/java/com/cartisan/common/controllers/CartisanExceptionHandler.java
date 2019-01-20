package com.cartisan.common.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author colin
 */
@RestControllerAdvice
public class CartisanExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
