package com.cartisan.joey.coupon.constants;

import com.cartisan.common.constants.CodeMessage;

/**
 * @author colin
 */
public class CouponCodeMessage extends CodeMessage {
    protected CouponCodeMessage(Integer code, String message) {
        super(code, message);
    }

    /**
     * 分类
     */
    public static CodeMessage CATEGORY_NOT_EXIST = new CouponCodeMessage(51001, "分类不存在");
    public static CodeMessage SAME_CATEGORY_NAME = new CouponCodeMessage(51002, "存在相同名称的分类");

}
