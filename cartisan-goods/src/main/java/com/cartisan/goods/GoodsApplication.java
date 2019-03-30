package com.cartisan.goods;

import com.cartisan.common.config.CartisanApplication;
import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author colin
 */
public class GoodsApplication extends CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
