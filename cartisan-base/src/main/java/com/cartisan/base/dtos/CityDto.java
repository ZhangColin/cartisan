package com.cartisan.base.dtos;

import com.cartisan.base.domains.City;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
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

    public static CityDto convertFrom(City city) {
        return new CityDto(city.getId(), city.getCode(),
                city.getName(), city.getEnglishName(),
                city.getFullPinYin(), city.getSimplePinYin(),
                city.getCountryId(), city.getCountryName(),
                city.getLatitude(), city.getLongitude());
    }
}
