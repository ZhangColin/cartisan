package com.cartisan.common.controllers;

import com.cartisan.common.constants.CommonCodeMessage;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.cartisan.common.responses.GenericResponse.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
public class CartisanExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public GenericResponse error(Exception e) {
        log.error("未处理异常：", e);
        return fail(CommonCodeMessage.SERVER_ERROR);
    }

    @ExceptionHandler(value = CartisanException.class)
    public GenericResponse error(CartisanException cartisanException) {
        return fail(cartisanException.getCodeMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public GenericResponse error(MethodArgumentNotValidException exception) {
        return fail(CommonCodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("\n"))
        ));
    }
}
