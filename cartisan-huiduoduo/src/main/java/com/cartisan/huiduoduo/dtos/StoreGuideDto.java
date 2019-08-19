package com.cartisan.huiduoduo.dtos;

import com.cartisan.common.CartisanContext;
import com.cartisan.huiduoduo.domains.StoreGuide;
import com.cartisan.huiduoduo.services.StoreService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author colin
 */
@Data
@RequiredArgsConstructor
public class StoreGuideDto {
    @NonNull
    private String couponSchemaId;
    @NonNull
    private String storeId;
    private String storeName;
    @NonNull
    private String guide;

    public static StoreGuideDto convertFrom(StoreGuide storeGuide, Long couponSchemaId) {
        final StoreGuideDto storeGuideDto = new StoreGuideDto(couponSchemaId.toString(),
                storeGuide.getStoreId().toString(), storeGuide.getGuide());

        storeGuideDto.setStoreName(
                CartisanContext.getBean(StoreService.class).getStore(storeGuide.getStoreId()).getName());

        return storeGuideDto;
    }
}
