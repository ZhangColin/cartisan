package com.cartisan.common.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author colin
 */
@Data
@Component
@ConfigurationProperties("cartisan.redis")
@RefreshScope
public class CartisanRedisConfig {
    private String root;
}
