package com.cartisan.controllers;

import com.cartisan.constants.CommonCodeMessage;
import com.cartisan.exceptions.CartisanException;
import com.cartisan.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CartisanException.class)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse error(CartisanException cartisanException) {
        return GenericResponse.fail(cartisanException.getCodeMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse error(MethodArgumentNotValidException exception) {
        return GenericResponse.fail(CommonCodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("\n"))
        ));
    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse error(DataAccessException e) {
        log.error("数据库操作异常：", e);
        return GenericResponse.fail(CommonCodeMessage.SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse error(RuntimeException e) {
        log.error("运行时异常：", e);
        return GenericResponse.fail(CommonCodeMessage.SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse error(Exception e) {
        log.error("未处理异常：", e);
        return GenericResponse.fail(CommonCodeMessage.SERVER_ERROR);
    }
}
