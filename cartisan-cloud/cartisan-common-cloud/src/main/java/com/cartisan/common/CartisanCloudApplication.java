package com.cartisan.common;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author colin
 */
@SpringCloudApplication
public class CartisanCloudApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }

//    @Bean
//    public ServletRegistrationBean hystrixStreamServlet() {
//        final HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
//        final ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean =
//                new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
//        // 系统启动时的加载顺序
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//
//        return registrationBean;
//    }
}
