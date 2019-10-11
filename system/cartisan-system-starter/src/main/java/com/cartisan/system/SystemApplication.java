package com.cartisan.system;

import com.cartisan.common.repositories.CartisanRepositoryFactoryBean;
import com.cartisan.common.utils.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author colin
 */
@SpringBootApplication(scanBasePackages = {"com.cartisan.common", "com.cartisan.system"})
@EnableJpaRepositories(basePackages = {"com.cartisan.system"},
        repositoryFactoryBeanClass = CartisanRepositoryFactoryBean.class)
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }
}
