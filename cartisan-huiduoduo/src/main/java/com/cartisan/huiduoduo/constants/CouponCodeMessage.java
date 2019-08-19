package com.cartisan.huiduoduo.constants;

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

    /**
     * 商户
     */
    public static CodeMessage MERCHANT_NOT_EXIST = new CouponCodeMessage(52001, "分类不存在");
    public static CodeMessage SAME_MERCHANT_NAME = new CouponCodeMessage(52002, "存在相同名称的商户");

    /**
     * 门店
     */
    public static CodeMessage STORE_NOT_EXIST = new CouponCodeMessage(53001, "门店不存在");
    public static CodeMessage SAME_STORE_NAME = new CouponCodeMessage(53002, "商户下存在相同名称的门店");


    /**
     * 微信用户
     */
    public static CodeMessage OPEN_ID_IS_NULL = new CouponCodeMessage(63001, "openId 不能为空");
    public static CodeMessage USER_NOT_EXIST = new CouponCodeMessage(63002, "用户不存在");
    public static CodeMessage USER_NOT_GET_COUPON = new CouponCodeMessage(63011, "用户没有领取过优惠券");
}
