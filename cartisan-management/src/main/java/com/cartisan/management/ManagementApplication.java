package com.cartisan.management;

import com.cartisan.common.config.CartisanApplication;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

	@Bean
	public ServletRegistrationBean hystrixStreamServlet() {
		final HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
		final ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean =
				new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
		// 系统启动时的加载顺序
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");

		return registrationBean;
	}
}
