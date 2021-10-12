//package com.cartisan.filters;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.filter.AbstractRequestLoggingFilter;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author colin
// */
//public class RequestLoggingFilter extends AbstractRequestLoggingFilter {
//    @Override
//    protected void beforeRequest(HttpServletRequest request, String message) {
//
//    }
//
//    @Override
//    protected void afterRequest(HttpServletRequest request, String message) {
//        if (!StringUtils.contains(request.getRequestURI(), "druid")
//                && !StringUtils.contains(request.getRequestURI(), "swagger")
//                && !StringUtils.contains(request.getRequestURI(), "api-docs")) {
//            this.logger.info(message);
//        }
//    }
//}
