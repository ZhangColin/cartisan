package com.cartisan.system;

import com.cartisan.common.CartisanCloudApplication;
import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
public class SystemCloudApplication  extends CartisanCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemCloudApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
