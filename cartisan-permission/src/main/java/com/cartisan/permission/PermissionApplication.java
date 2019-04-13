package com.cartisan.permission;

import com.cartisan.common.configs.CartisanApplication;
import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
public class PermissionApplication extends CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
