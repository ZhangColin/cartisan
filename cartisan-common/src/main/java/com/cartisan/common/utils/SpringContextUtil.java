//package com.cartisan.common.utils;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @author colin
// */
//@Component
//public class SpringContextUtil implements ApplicationContextAware {
//    private static ApplicationContext context;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//
//        context = applicationContext;
//    }
//
//    /// 获取当前环境
//    public static String getActiveProfile() {
//        return context.getEnvironment().getActiveProfiles()[0];
//    }
//}
