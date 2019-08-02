package com.cartisan.huiduoduo.dtos;

import com.cartisan.common.CartisanContext;
import com.cartisan.huiduoduo.domains.Store;
import com.cartisan.huiduoduo.services.MerchantService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;


/**
 * @author colin
 */
@Data
@RequiredArgsConstructor
public class StoreDto {
    @NonNull
    private String id;

    @NonNull
    private String merchantId;

    private String merchantName;

    private String name;

    private String phone;

    private String area;

    private String address;

    private String description;

    private Integer sort;

    public static StoreDto convertFrom(Store store) {
        StoreDto storeDto = new StoreDto(store.getId().toString(), store.getMerchantId().toString());
        BeanUtils.copyProperties(store, storeDto);

        storeDto.setMerchantName(CartisanContext.getBean(MerchantService.class).getMerchant(store.getMerchantId()).getName());

        return storeDto;
    }
}
