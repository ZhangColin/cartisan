package com.cartisan.responses;

import com.cartisan.constants.CodeMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author colin
 */
@Getter
public class ResponseUtil {

    public static final String SUCCESS_MESSAGE = "执行成功。";


    public static ResponseEntity<?> success() {
        return successWithMessage(SUCCESS_MESSAGE);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static ResponseEntity<?> successWithMessage(String message) {
        final CodeMessage codeMessage = CodeMessage.SUCCESS.fillArgs(message);
        return new ResponseEntity<>(new GenericResponse(codeMessage.getCode(), codeMessage.getMessage()),
                HttpStatus.OK);
    }

    public static  <T> ResponseEntity<GenericResponseWithData<T>> successWithMessage(T data) {
        return successWithMessage(SUCCESS_MESSAGE, data);
    }

    public static  <T> ResponseEntity<GenericResponseWithData<T>> successWithMessage(String message, T data) {
        final CodeMessage codeMessage = CodeMessage.SUCCESS.fillArgs(message);
        return new ResponseEntity<>(new GenericResponseWithData<>(codeMessage.getCode(), codeMessage.getMessage(), data),
                HttpStatus.OK);
    }

    public static  <T> ResponseEntity<GenericResponseWithData<T>> successWithMessage(CodeMessage codeMessage, T data) {
        return new ResponseEntity<>(new GenericResponseWithData<>(codeMessage.getCode(), codeMessage.getMessage(), data),
                HttpStatus.OK);
    }

    public static ResponseEntity<?> fail(String message) {
        final CodeMessage codeMessage = CodeMessage.FAIL.fillArgs(message);
        return new ResponseEntity<>(new GenericResponse(codeMessage.getCode(), codeMessage.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> fail(CodeMessage codeMessage) {
        return new ResponseEntity<>(new GenericResponse(codeMessage.getCode(), codeMessage.getMessage()),
                Optional.ofNullable(HttpStatus.resolve(codeMessage.getCode())).orElse(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public static ResponseEntity<?> fail(CodeMessage codeMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new GenericResponse(codeMessage.getCode(), codeMessage.getMessage()), httpStatus);
    }
}
