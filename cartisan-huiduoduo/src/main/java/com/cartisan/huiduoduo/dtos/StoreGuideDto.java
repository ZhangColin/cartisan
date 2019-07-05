package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.StoreGuide;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class StoreGuideDto {
    private String storeId;
    private String guide;

    public static StoreGuideDto convertFrom(StoreGuide storeGuide) {
        return new StoreGuideDto(
                storeGuide.getStoreId().toString(), storeGuide.getGuide());
    }
}
