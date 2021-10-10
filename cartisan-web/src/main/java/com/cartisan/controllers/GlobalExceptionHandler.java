package com.cartisan.controllers;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exception.CartisanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static com.cartisan.responses.ResponseUtil.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
@Order(99)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> error(HttpRequestMethodNotSupportedException exception) {
        return fail(CodeMessage.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = CartisanException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> error(CartisanException exception) {
        log.warn("业务处理异常：{}", exception.getMessage());
        return fail(exception.getCodeMessage());
    }

    /**
     * 数据验证异常
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> error(MethodArgumentNotValidException exception) {
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
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> error(ConstraintViolationException exception) {
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
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable exception){
        log.error("未处理异常：", exception);
        return fail(CodeMessage.UNKNOWN);
    }

    @ExceptionHandler(value = DataAccessException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> error(DataAccessException exception) {
        log.error("数据库操作异常：", exception);
        return fail(CodeMessage.INTERNAL_SERVER_ERROR);
    }
}
