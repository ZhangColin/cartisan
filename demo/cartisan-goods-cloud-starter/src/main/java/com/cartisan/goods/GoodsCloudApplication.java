package com.cartisan.goods;

import com.cartisan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
@SpringCloudApplication
public class GoodsCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsCloudApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
