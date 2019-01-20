package com.cartisan.base.config;

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
//    @Value("${server.servlet.context-path}")
//    private String contextPath;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.cartisan.base.controllers"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(Byte.class, Integer.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cartisan base Restful APIs")
//                .description("查看数据源：<a href=\""+contextPath+"/druid/index.html\">druid</a>")
                .termsOfServiceUrl("http://www.cartisan.com")
                .contact(new Contact("colin", "", "zhang_colin@163.com"))
                .version("1.0")
                .build();
    }
}

