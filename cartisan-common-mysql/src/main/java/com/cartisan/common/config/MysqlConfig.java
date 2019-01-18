package com.cartisan.common.config;

import com.cartisan.common.repositories.CartisanRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author colin
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.cartisan"},
        repositoryFactoryBeanClass = CartisanRepositoryFactoryBean.class)
public class MysqlConfig {
}
