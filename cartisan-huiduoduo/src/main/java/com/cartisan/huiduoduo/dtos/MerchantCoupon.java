package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.domains.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class MerchantCoupon {
    private String id;

    private String name;

    private String logo;
    List<CouponInfo> coupons;

    public static MerchantCoupon convertFrom(Merchant merchant, List<CouponSchema> couponSchemas) {
        return new MerchantCoupon(
                merchant.getId().toString(), merchant.getName(), merchant.getLogo(),
                couponSchemas.stream().map(CouponInfo::convertFrom).collect(toList()));
    }
}
