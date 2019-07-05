package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Store;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author colin
 */
@Data
public class StoreDto {
    private String id;

    private String merchantId;

    private String name;

    private String phone;

    private String area;

    private String address;

    private String description;

    private Integer sort;

    public static StoreDto convertFrom(Store store) {
        StoreDto storeDto = new StoreDto();
        BeanUtils.copyProperties(store, storeDto);

        return storeDto;
    }
}
