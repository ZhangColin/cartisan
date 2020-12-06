package com.cartisan.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author colin
 */
@Slf4j
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        if (!StringUtils.contains(request.getRequestURI(), "druid")
                && !StringUtils.contains(request.getRequestURI(), "swagger")
                && !StringUtils.contains(request.getRequestURI(), "api-docs")) {
            this.logger.info(message);
        }
    }
}
