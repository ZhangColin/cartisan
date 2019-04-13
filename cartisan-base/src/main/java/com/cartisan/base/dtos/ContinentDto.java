package com.cartisan.base.dtos;

import com.cartisan.base.domains.Continent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinentDto {
    private Long id;
    private String code;
    private String name;

    public static ContinentDto convertFrom(Continent continent) {
        return new ContinentDto(continent.getId(), continent.getCode(), continent.getName());
    }
}
