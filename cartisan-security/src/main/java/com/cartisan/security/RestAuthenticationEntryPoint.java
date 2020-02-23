package com.cartisan.security;

import com.cartisan.constants.CodeMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 * @author colin
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 当用户尝试访问安全的 REST 资源而不提供任何凭据时，将调用此方法发送 401 响应
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Optional.ofNullable(authException)
//                .map(AuthenticationException::getMessage).orElse("Unauthorized"));

        response.sendError(CodeMessage.UNAUTHORIZED.getCode(), CodeMessage.UNAUTHORIZED.getMessage());
    }
}
