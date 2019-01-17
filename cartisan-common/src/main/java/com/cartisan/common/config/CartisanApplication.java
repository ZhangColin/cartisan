package com.cartisan.common.config;

import com.cartisan.common.repositories.CartisanRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Title: CartisanApplication</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@SpringBootApplication
@ComponentScan(value = "com.cartisan.common")
@EnableJpaRepositories(repositoryFactoryBeanClass = CartisanRepositoryFactoryBean.class)
public class CartisanApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }
}
