//package com.cartisan.interceptors;
//
//import com.cartisan.CartisanContext;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
///**
// * @author colin
// */
//@Slf4j
//@Component
//public class HttpInterceptor extends HandlerInterceptorAdapter {
//    private static final String START_TIME = "requestStartTime";
//
//    private final ObjectMapper objectMapper;
//
//    public HttpInterceptor(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI();
//        Map parameterMap = request.getParameterMap();
//
//        long start = System.currentTimeMillis();
//        request.setAttribute(START_TIME, start);
//
//        log.info("request start. url: [{}], params: [{}]", url, objectMapper.writeValueAsString(parameterMap));
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////        String url = request.getRequestURI();
////
////        long start = (Long) request.getAttribute(START_TIME);
////        long end = System.currentTimeMillis();
////
////        log.info("request finished. url:{}, cost: {}", url, end - start);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String url = request.getRequestURI();
//
//        long start = (Long) request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//
//        log.info("request complete. url: [{}], cost: [{}]", url, end - start);
//
//    }
//}
