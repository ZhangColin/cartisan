package com.cartisan.configs;

import com.cartisan.controllers.CartisanRequestMappingHandlerMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author colin
 */
@Configuration
@Slf4j
public class MvcConfig extends WebMvcConfigurationSupport {
//    @Bean
//    public FilterRegistrationBean requestLogFilter() {
//        RequestLoggingFilter filter = new RequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludeClientInfo(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(5120);
//
//        return new FilterRegistrationBean(filter);
//    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new CartisanRequestMappingHandlerMapping();
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors();
        configurer.registerDeferredResultInterceptors();
    }

    //    @Bean
//    public UrlInterceptor urlInterceptor() {
//        return new UrlInterceptor();
//    }
//
//    @Bean
//    public AnnotationInterceptor annotationInterceptor() {
//        return new AnnotationInterceptor();
//    }


//    @Autowired
//    private HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(urlInterceptor());
//
//        // 排除配置
//        addInterceptor.excludePathPatterns("/error");
//        addInterceptor.excludePathPatterns("/login**");
//
//        // 拦截配置
//        addInterceptor.addPathPatterns("/**");

//        registry.addInterceptor(annotationInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(jwtFilter).addPathPatterns("/**").excludePathPatterns("/**/login");

//        registry.addInterceptor(signatureInterceptor).addPathPatterns("/**");

//        registry.addInterceptor(httpInterceptor).addPathPatterns("/**");
    }
}
