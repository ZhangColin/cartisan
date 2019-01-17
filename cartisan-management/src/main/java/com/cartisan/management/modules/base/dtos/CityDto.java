package com.cartisan.management.modules.base.dtos;

import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class CityDto {
    private Long id;
    private String code;
    private String name;
    private String englishName;
    private String fullPinYin;
    private String simplePinYin;
    private Long countryId;
    private String countryName;
    private String latitude;
    private String longitude;
}
