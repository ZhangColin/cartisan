package com.cartisan.configs;

import com.cartisan.CartisanContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Configuration
@Slf4j
public class MvcConfig extends WebMvcConfigurationSupport {
//    @Bean
//    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
//        RequestLoggingFilter filter = new RequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludeClientInfo(true);
//        filter.setIncludeHeaders(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(5120);
//
//        return new FilterRegistrationBean<>(filter);
//    }

//    @Override
//    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
//        return new CartisanRequestMappingHandlerMapping();
//    }


    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        final Collection<HttpMessageConverter> customConverters = CartisanContext.getBeansOfType(HttpMessageConverter.class).values();
        final Set<? extends Class<? extends HttpMessageConverter>> customConverterTypes = customConverters.stream().map(converter -> converter.getClass()).collect(Collectors.toSet());
        converters.removeIf(converter-> customConverterTypes.contains(converter.getClass()));
        customConverters.forEach(converter->converters.add(0, converter));

        super.extendMessageConverters(converters);
    }

    @Override
    public FormattingConversionService mvcConversionService() {
        final FormattingConversionService formattingConversionService = super.mvcConversionService();

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(formattingConversionService);

        return formattingConversionService;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);

        // 注册 Spring data jpa pageable 的参数分解器
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
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
