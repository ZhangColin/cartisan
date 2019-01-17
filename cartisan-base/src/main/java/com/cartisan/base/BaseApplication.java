package com.cartisan.base;

import com.cartisan.common.config.CartisanApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <p>Title: BaseApplication</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@SpringCloudApplication
public class BaseApplication extends CartisanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }
}
