package com.cartisan.aop.resubmit;

import java.lang.annotation.*;

/**
 * @author colin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {
    /**
     * 延时时间 在延时多久后可以再次提交，单位：秒
     */
    int delaySeconds() default 20;
}
