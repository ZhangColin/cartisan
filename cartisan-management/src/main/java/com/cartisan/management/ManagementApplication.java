package com.cartisan.management;

import com.cartisan.common.CartisanApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author colin
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ManagementApplication extends CartisanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

}
