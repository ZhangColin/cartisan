package com.cartisan.infrastructure.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangcolin
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.SOURCE)
public @interface BoundedContext {
    String name();

    SubDomain subDomain() default SubDomain.Core;
}
