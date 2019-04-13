package com.cartisan.common.controller;

import com.cartisan.common.response.CodeMessage;
import com.cartisan.common.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cartisan.common.response.GenericResponse.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
public class CartisanExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public GenericResponse error(Exception e) {
        log.error("未处理异常：", e);
        return fail(CodeMessage.SERVER_ERROR);
    }
}
