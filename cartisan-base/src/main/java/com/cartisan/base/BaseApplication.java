package com.cartisan.base;

import com.cartisan.common.config.CartisanApplication;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * <p>Title: BaseApplication</p>
 * <p>Description: </p>
 *
 * @author colin
 */
public class BaseApplication extends CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }


}
