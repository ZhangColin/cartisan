package com.cartisan.security;

import com.cartisan.CartisanContext;
import com.cartisan.constants.CodeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cartisan.responses.ResponseUtil.fail;

/**
 * 自定义返回结果：没有权限访问时
 * @author colin
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        final ObjectMapper objectMapper = CartisanContext.getBean(ObjectMapper.class);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(fail(CodeMessage.FORBIDDEN)));
        response.getWriter().flush();
    }
}
