package com.cartisan.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author colin
 */
@Data
@Component
@ConfigurationProperties("cartisan")
@RefreshScope
public class CartisanConfig {
    private String name;
    private String version;
}
