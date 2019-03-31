package com.cartisan.goods.dto;

import com.cartisan.goods.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Long id;
    private String name;
    private String firstLetter;
    private Boolean isManufacturer;
    private Boolean isShow;
    private String logo;
    private String bigPic;
    private Long sort;

    public static BrandDto convertFrom(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName(), brand.getFirstLetter(),
                brand.getIsManufacturer(), brand.getIsShow(), brand.getLogo(),
                brand.getBigPic(), brand.getSort());
    }
}
