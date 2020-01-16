package com.cartisan.security;

import com.cartisan.CartisanContext;
import com.cartisan.constants.CodeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cartisan.responses.ResponseUtil.fail;

/**
 * 自定义返回结果：未登录或登录过期
 * @author colin
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final ObjectMapper objectMapper = CartisanContext.getBean(ObjectMapper.class);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(fail(CodeMessage.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
