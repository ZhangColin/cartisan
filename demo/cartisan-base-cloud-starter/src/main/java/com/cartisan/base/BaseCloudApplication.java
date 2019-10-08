package com.cartisan.base;

import com.cartisan.common.CartisanCloudApplication;
import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
public class BaseCloudApplication extends CartisanCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseCloudApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
