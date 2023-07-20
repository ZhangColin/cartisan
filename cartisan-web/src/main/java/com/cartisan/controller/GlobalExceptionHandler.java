package com.cartisan.controller;

import com.cartisan.constant.CodeMessage;
import com.cartisan.exception.CartisanException;
import com.cartisan.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static com.cartisan.response.ResponseUtil.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public GenericResponse error(HttpRequestMethodNotSupportedException exception) {
        log.warn(exception.getMessage());
        return fail(CodeMessage.METHOD_NOT_ALLOWED.fillArgs(exception.getMethod()));
    }

    @ExceptionHandler(value = CartisanException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse error(CartisanException exception) {
        log.warn("业务处理异常：{}", exception.getMessage());
        return fail(exception.getCodeMessage());
    }

    /**
     * 数据验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse error(MethodArgumentNotValidException exception) {
        final CodeMessage errorMessage = CodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("\n"))
        );

        log.warn("数据校验异常：{}", errorMessage.getMessage());
        return fail(errorMessage);
    }

    /**
     * 数据验证异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public GenericResponse error(ConstraintViolationException exception) {
        final CodeMessage errorMessage = CodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("\n"))
        );

        log.warn("数据校验异常：{}", errorMessage.getMessage());
        return fail(errorMessage);
    }

    /**
     * 未处理异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse handleException(Throwable exception){
        log.error("未处理异常：", exception);
        return fail(CodeMessage.UNKNOWN);
    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse error(DataAccessException exception) {
        log.error("数据库操作异常：", exception);
        return fail(CodeMessage.INTERNAL_SERVER_ERROR);
    }
}
