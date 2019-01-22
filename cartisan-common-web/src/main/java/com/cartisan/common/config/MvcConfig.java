package com.cartisan.common.config;

import com.cartisan.common.filter.RequestLoggingFilter;
import com.cartisan.common.interceptor.SignatureInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author colin
 */
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    @Qualifier("outerObjectMapper")
    private ObjectMapper outerObjectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createMappingJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter createMappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(outerObjectMapper);

        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public FilterRegistrationBean requestLogFilter() {
        RequestLoggingFilter filter = new RequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(5120);

        return new FilterRegistrationBean(filter);
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
//    private JwtFilter jwtFilter;

    @Autowired
    private SignatureInterceptor signatureInterceptor;

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

        registry.addInterceptor(signatureInterceptor).addPathPatterns("/**");
    }
}
