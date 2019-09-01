package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.domains.Merchant;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class CouponSummaryInfo {
    private String merchantId;
    private String merchantName;
    private String merchantLogo;

    private String couponId;
    private String couponTitle;
    private String couponIntroduction;
    private Integer commission;

    private Boolean alreadyGet;

    public static CouponSummaryInfo convertFrom(Merchant merchant, CouponSchema couponSchema, Boolean alreadyGet) {
        final CouponSummaryInfo couponSummaryInfo = new CouponSummaryInfo();
        couponSummaryInfo.setMerchantId(merchant.getId().toString());
        couponSummaryInfo.setMerchantName(merchant.getName());
        couponSummaryInfo.setMerchantLogo(merchant.getLogo());

        couponSummaryInfo.setCouponId(couponSchema.getId().toString());
        couponSummaryInfo.setCouponTitle(couponSchema.getTitle());
        couponSummaryInfo.setCouponIntroduction(couponSchema.getIntroduction());
        couponSummaryInfo.setCommission(couponSchema.getCommission());

        couponSummaryInfo.setAlreadyGet(alreadyGet);

        return couponSummaryInfo;
    }
}
