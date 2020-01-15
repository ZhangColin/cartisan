package com.cartisan.configs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author colin
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Autowired
    private SystemInfo systemInfo;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(systemInfo.getTitle())
//                .description("查看数据源：<a href=\""+contextPath+"/druid/index.html\">druid</a>")
                .termsOfServiceUrl(systemInfo.getServiceUrl())
                .contact(new Contact(systemInfo.getContact().getName(),
                        systemInfo.getContact().getUrl(), systemInfo.getContact().getEmail()))
                .version(systemInfo.getVersion())
                .build();
    }
}

