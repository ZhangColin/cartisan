package com.cartisan.joey.coupon;

import com.cartisan.common.configs.CartisanApplication;
import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
public class JoeyCouponApplication extends CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(JoeyCouponApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
