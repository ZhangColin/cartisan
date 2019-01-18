package com.cartisan.common.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要拦截的 controller 方法上使用些注解
 * <p>Title: UseAnnotationRequired</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseAnnotationRequired {
}
