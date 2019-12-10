package com.cartisan.repositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author colin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {
    /**
     * @return 基本对象的属性名
     */
    String propName() default "";

    /**
     * @return 查询方式，默认等于
     */
    Type type() default Type.EQUAL;

    /**
     * @return 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开
     */
    String blurry() default "";

    enum Type{
        /**
         * 等于
         */
        EQUAL,
        /**
         * 不等于
         */
        NOT_EQUAL,
        /**
         * 大于等于
         */
        GREATER_EQUAL,
        /**
         * 大于
         */
        GREATER,
        /**
         * 小于等于
         */
        LESS_EQUAL,
        /**
         * 小于
         */
        LESS,
        /**
         * 中模糊
         */
        INNER_LIKE,
        /**
         * 左模糊
         */
        LEFT_LIKE,
        /**
         * 右模糊
         */
        RIGHT_LIKE,
        /**
         * 包含
         */
        IN,
        /**
         * 在..区间内
         */
        BETWEEN
    }
}
