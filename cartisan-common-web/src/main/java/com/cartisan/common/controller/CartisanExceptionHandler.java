package com.cartisan.common.controller;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.common.response.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author colin
 */
@RestControllerAdvice
public class CartisanExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public GenericResponse error(Exception e) {
        return new GenericResponse(false, StatusCode.ERROR, e.getMessage());
    }
}
