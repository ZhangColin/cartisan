package com.cartisan.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author colin
 */
@Slf4j
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info("请求开始");
        MDC.put("identify", RandomStringUtils.random(10));
        super.beforeRequest(request, message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        MDC.remove("identify");
        super.afterRequest(request, message);
    }
}
