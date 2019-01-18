package com.cartisan.gateway;

import com.cartisan.common.config.CartisanApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
public class GatewayApplication extends CartisanApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}

