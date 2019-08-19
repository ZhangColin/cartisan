package com.cartisan.huiduoduo.dtos;

import com.cartisan.common.CartisanContext;
import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.domains.Merchant;
import com.cartisan.huiduoduo.domains.Store;
import com.cartisan.huiduoduo.domains.StoreGuide;
import com.cartisan.huiduoduo.repositories.StoreRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;

/**
 * @author colin
 */
@Data
@RequiredArgsConstructor
public class CouponInfo {
    private String merchantId;
    private String merchantName;
    private String merchantLogo;

    private String couponId;
    private String couponTitle;
    private String couponIntroduction;

    private Boolean alreadyGet;

    private String code;
    private String image;
    private String validStart;
    private String validEnd;
    private String codeImage;

    private String storeId;
    private String storeName;
    private String storeDescription;
    private String storeGuide;

    public static CouponInfo convertFrom(Merchant merchant, CouponSchema couponSchema, Boolean alreadyGet) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        final CouponInfo couponInfo = new CouponInfo();
        couponInfo.setMerchantId(merchant.getId().toString());
        couponInfo.setMerchantName(merchant.getName());
        couponInfo.setMerchantLogo(merchant.getLogo());

        couponInfo.setCouponId(couponSchema.getId().toString());
        couponInfo.setCouponTitle(couponSchema.getTitle());
        couponInfo.setCouponIntroduction(couponSchema.getIntroduction());

        couponInfo.setAlreadyGet(alreadyGet);

        couponInfo.setCode(couponSchema.getId().toString());
        couponInfo.setImage(couponSchema.getImage());
        couponInfo.setValidStart(sdf.format(couponSchema.getValidStart()));
        couponInfo.setValidEnd(sdf.format(couponSchema.getValidEnd()));
        couponInfo.setCodeImage(couponSchema.getCodeImage());

        if (couponSchema.getStoreGuides().size() > 0) {
            final StoreGuide storeGuide = couponSchema.getStoreGuides().get(0);
            couponInfo.setStoreGuide(storeGuide.getGuide());
            couponInfo.setStoreId(storeGuide.getStoreId().toString());

            final Store store = CartisanContext.getBean(StoreRepository.class).findById(storeGuide.getStoreId()).get();
            couponInfo.setStoreName(store.getName());
            couponInfo.setStoreDescription(store.getDescription());
        }

        return couponInfo;
    }
}
