package com.cartisan.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: UrlInterceptor</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Slf4j
public class UrlInterceptor extends HandlerInterceptorAdapter {
    public static final String NO_INTERCEPTOR_PATH = ".*/((.css)|(.js)|(images)|(login)|(anon)).*";
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于 URL 实现的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(NO_INTERCEPTOR_PATH)) {
            // 不需要拦截，直接过
            return true;
        } else {
            log.info("@@@ 被 UrlInterceptor 拦截 @@@");
            return true;
        }
    }


}
