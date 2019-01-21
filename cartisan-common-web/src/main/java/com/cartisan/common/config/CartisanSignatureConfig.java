package com.cartisan.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author colin
 */
@Data
@Component
@ConfigurationProperties("cartisan.signature")
@RefreshScope
public class CartisanSignatureConfig {
    private boolean enable;
    private String secret;
}
