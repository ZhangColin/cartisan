package com.cartisan.system;

import com.cartisan.common.utils.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author colin
 */
@SpringCloudApplication
@ComponentScan(basePackages = {"com.cartisan.common", "com.cartisan.system"})
@MapperScan("com.cartisan.system.queries")
public class SystemCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemCloudApplication.class);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }
}
