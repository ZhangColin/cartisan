package com.cartisan.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author colin
 */
@Component
@Slf4j
@Order(10)
public class RequestLogFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public RequestLogFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!StringUtils.contains(request.getRequestURI(), "druid")
                && !StringUtils.contains(request.getRequestURI(), "swagger")
                && !StringUtils.contains(request.getRequestURI(), "api-docs")) {

            String query = request.getQueryString() != null ? "?" + request.getQueryString() : "";
            if (request.getMethod().equals(HttpMethod.POST.name()) || request.getMethod().equals(HttpMethod.PUT.name())) {
                final MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest(request);

                log.info("IP:[{}], Method:[{}], URI:[{}], Body:[{}], Parameters:[{}]", request.getRemoteAddr(), request.getMethod(),
                        request.getRequestURI() + query,
                        multiReadHttpServletRequest.getRequestBody(),
                        objectMapper.writeValueAsString(multiReadHttpServletRequest.getParameterMap()));

                filterChain.doFilter(multiReadHttpServletRequest, response);
            }
            else {
                log.info("IP:[{}], Method:[{}], URI:[{}]", request.getRemoteAddr(), request.getMethod(),
                        request.getRequestURI() + query);

                filterChain.doFilter(request, response);
            }
        }
        else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * HttpServletRequest 请求体多读
     */
    class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
        // 缓存 RequestBody
        private String requestBody;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request the {@link HttpServletRequest} to be wrapped.
         * @throws IllegalArgumentException if the request is null
         */
        public MultiReadHttpServletRequest(HttpServletRequest request) {
            super(request);
            requestBody = "";
            try {
                final StringBuilder stringBuilder = new StringBuilder();
                final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                requestBody = stringBuilder.toString();
            } catch (IOException e) {
                logger.error(e);
            }
        }

        @Override
        public ServletInputStream getInputStream() {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));

            return new ServletInputStream() {
                @Override
                public int read() {
                    return byteArrayInputStream.read();
                }

                @Override
                public boolean isFinished() {
                    return byteArrayInputStream.available() == 0;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }
            };
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(this.getInputStream()));
        }

        public String getRequestBody() {
            return requestBody.replaceAll("\n", "");
        }
    }
}
