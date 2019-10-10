package com.cartisan.system.config;

import com.cartisan.common.repositories.CartisanRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author colin
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.cartisan.system"},
        repositoryFactoryBeanClass = CartisanRepositoryFactoryBean.class)
@EntityScan(basePackages = {"com.cartisan.system"})
public class SystemConfig {
}
