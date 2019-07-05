package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class CouponSchemaDto {
    private String id;
    private String name;
    private String icon;
    private Integer sort;

    public static CouponSchemaDto convertFrom(Category category) {
        return new CouponSchemaDto(
                category.getId().toString(), category.getName(), category.getIcon(), category.getSort());
    }
}
