package com.cartisan.common.config;

import com.cartisan.common.filters.RequestLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: MvcConfig</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private Environment environment;

    //    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//    }
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//
//        return new HttpMessageConverters(fastJsonHttpMessageConverter);
//    }

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
    }

    // 跨域问题由网关层统一处理
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        final String activeProfile = environment.getProperty("spring.profiles.active");
//        log.info("cartisan-common: log active profile [{}]", activeProfile);
//        if (activeProfile.equals("dev")) {
//            registry.addMapping("/**");
//        }
//    }
}
