package com.cartisan.aop.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author colin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    /**
     * @return 资源名称，用于描述接口功能
     */
    String name() default "";

    /**
     * @return 资源 key
     */
    String key() default "";

    /**
     * @return key 前缀
     */
    String prefix() default "";

    /**
     * @return 限流时间区间，单位秒
     */
    int period();

    /**
     * @return 限制访问次数
     */
    int count();

    /**
     * @return 限制类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
