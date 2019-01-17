package com.cartisan.management.modules.base.dtos;

import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class AirportDto{
    private Long id;
    private String code;
    private String name;
    private String englishName;
    private String fullPinYin;
    private String simplePinYin;
    private Long cityId;
    private String cityName;
    private String latitude;
    private String longitude;
}
