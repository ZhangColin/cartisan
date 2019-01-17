package com.cartisan.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: ZuulConfig</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class ZuulConfig {
    /**
     * 实现 zuul 配置的动态修改
     * @return
     */
    @ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
