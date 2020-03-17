package com.cartisan.controllers;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exceptions.CartisanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.cartisan.exceptions.ThrowableUtil.getStackTrace;
import static com.cartisan.responses.ResponseUtil.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Slf4j
@Order(99)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CartisanException.class)
    public ResponseEntity<?> error(CartisanException cartisanException) {
        log.error("业务处理异常：", cartisanException);
        return fail(cartisanException.getCodeMessage());
    }

    /**
     * 数据验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> error(MethodArgumentNotValidException exception) {
        log.error("数据验证失败：{}", getStackTrace(exception));
        return fail(CodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("\n"))
        ));
    }

    /**
     * 未处理异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable e){
        log.error("未处理异常：", e);
        return fail(CodeMessage.UNKNOWN);
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<?> error(DataAccessException e) {
        log.error("数据库操作异常：", e);
        return fail(CodeMessage.INTERNAL_SERVER_ERROR);
    }
}
