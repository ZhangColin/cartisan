package com.cartisan.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author colin
 */
@Component
@Slf4j
@Order(0)
public class MDCFilter extends OncePerRequestFilter {

    public static final String REQUEST_ID_KEY = "request-id";
    public static final String USER_KEY = "user";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            MDC.put(REQUEST_ID_KEY, getRequestId(request));
            MDC.put(USER_KEY, request.getRemoteUser());
            response.addHeader(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private String getRequestId(HttpServletRequest request) {
        final String headerRequestId = request.getHeader(REQUEST_ID_KEY);
        final String parameterRequestId = request.getParameter(REQUEST_ID_KEY);

        if (!StringUtils.isEmpty(headerRequestId)) {
            return headerRequestId;
        }

        if (!StringUtils.isEmpty(parameterRequestId)) {
            return parameterRequestId;
        }

        return RandomStringUtils.randomAlphabetic(10);
    }
}
