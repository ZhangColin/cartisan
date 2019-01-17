package com.cartisan.base.dtos;

import com.cartisan.base.domains.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: CountryDto</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
public class CountryDto {
    private Long id;
    private String code;
    private String name;
    private String englishName;
    private String fullPinYin;
    private String simplePinYin;
    private Long continentId;
    private String continentName;

    public static CountryDto convertFrom(Country country) {
        return new CountryDto(country.getId(), country.getCode(), country.getName(),
                country.getEnglishName(), country.getFullPinYin(), country.getSimplePinYin(),
                country.getContinentId(), country.getContinentName());
    }
}
