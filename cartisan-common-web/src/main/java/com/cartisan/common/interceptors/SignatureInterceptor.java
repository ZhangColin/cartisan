//package com.cartisan.common.interceptors;
//
//import com.cartisan.common.configs.CartisanSignatureConfig;
//import com.cartisan.common.responses.GenericResponse;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.cartisan.common.responses.GenericResponse.fail;
//
///**
// * 签名验证拦截器，现规则比较简单，可以使用 Json Web Token 或其它方式替代
// * @author colin
// */
//@Slf4j
//@Component
//public class SignatureInterceptor extends HandlerInterceptorAdapter {
//    @Value("${spring.profiles.active}")
//    private String env;
//
//    @Autowired
//    private CartisanSignatureConfig signatureConfig;
//
//    @Autowired
//    @Qualifier("outerObjectMapper")
//    private ObjectMapper outerObjectMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!signatureConfig.isEnable()) {
//            return true;
//        }
//
//        if (validate(request)) {
//            return true;
//        }
//
//        GenericResponse result = fail(JwtCodeMessage.SIGNATURE_ERROR);
//
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        response.setStatus(HttpStatus.OK.value());
//
//        response.getWriter().write(outerObjectMapper.writeValueAsString(result));
//
//        return false;
//    }
//
//    /**
//     * 一个简单的签名认证，规则：
//     * 1、将请求参数按 ascii 码排序
//     * 2、拼接为 a=value&b=value... 这样的字符串（不包含 sign）
//     * 3、混合密钥（secret）进行 md5 获得签名，与请求签名进行比较
//     */
//    private boolean validate(HttpServletRequest request) throws JsonProcessingException {
//        // 获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
//        final String requestSign = request.getParameter("sign");
//        if (StringUtils.isEmpty(requestSign)) {
//            return false;
//        }
//
//        final List<String> keys = new ArrayList<>(request.getParameterMap().keySet());
//        keys.remove("sign");
//        Collections.sort(keys);
//
//        final String parameterString = String.join("&",
//                keys.stream().map(key -> key + "=" + request.getParameter(key)).collect(Collectors.toList()));
//
//        final String sign = DigestUtils.md5Hex(parameterString);
//
//        final boolean result = StringUtils.equals(requestSign, sign);
//
//        if (!result) {
//            log.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}，正确签名应该为：{}",
//                    request.getRequestURI(), getIpAddress(request),
//                    outerObjectMapper.writeValueAsString(request.getParameterMap()),
//                    sign);
//        }
//
//        return result;
//    }
//
//    private String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//
//        // 如果是多级代理，那么取第一个 ip 为客户端 ip
//        if (ip != null && ip.indexOf(",") != -1) {
//            ip = ip.substring(0, ip.indexOf(",")).trim();
//        }
//
//        return ip;
//    }
//}
