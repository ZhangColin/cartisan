package com.cartisan.system;

import com.cartisan.common.utils.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
@SpringCloudApplication
public class SystemCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemCloudApplication.class);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }
}
