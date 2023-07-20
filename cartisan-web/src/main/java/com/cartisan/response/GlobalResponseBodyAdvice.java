package com.cartisan.response;

import com.cartisan.constant.CodeMessage;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

import static com.cartisan.response.ResponseUtil.fail;
import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof GenericResponse) {
            return o;
        }

        if (o instanceof LinkedHashMap){
            LinkedHashMap map = (LinkedHashMap) o;
            if (map.get("status").equals(404)){
                return fail(CodeMessage.NOT_FOUND.fillArgs(map.get("path").toString()));
            }
        }

        return success(o);
    }
}
