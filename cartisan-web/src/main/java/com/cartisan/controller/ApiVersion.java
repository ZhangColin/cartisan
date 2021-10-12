package com.cartisan.controller;

import java.lang.annotation.*;

/**
 * @author colin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 标识版本号，从1开始
     */
    int value() default 1;
}
