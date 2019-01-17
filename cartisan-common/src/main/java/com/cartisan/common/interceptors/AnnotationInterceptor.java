package com.cartisan.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>Title: AnnotationInterceptor</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Slf4j
public class AnnotationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        UseAnnotationRequired annotation = method.getAnnotation(UseAnnotationRequired.class);
        if (annotation != null) {
            return true;
        }
        else {
            log.info("@@@ 被 AnnotationInterceptor 拦截 @@@");
            return true;
        }
    }
}
