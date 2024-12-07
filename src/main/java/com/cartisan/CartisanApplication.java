package com.cartisan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangcolin
 */
@MapperScan(value = "com.cartisan.**.mapper")
@SpringBootApplication
public class CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartisanApplication.class, args);
    }
}
