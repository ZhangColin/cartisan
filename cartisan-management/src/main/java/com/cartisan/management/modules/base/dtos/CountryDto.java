package com.cartisan.management.modules.base.dtos;

import lombok.Data;

/**
 * <p>Title: CountryDto</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class CountryDto {
    private Long id;
    private String code;
    private String name;
    private String englishName;
    private String fullPinYin;
    private String simplePinYin;
    private Long continentId;
    private String continentName;
}
