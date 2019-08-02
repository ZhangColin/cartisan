package com.cartisan.huiduoduo.dtos;

import com.cartisan.common.CartisanContext;
import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.services.CategoryService;
import com.cartisan.huiduoduo.services.MerchantService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author colin
 */
@Data
@RequiredArgsConstructor
public class CouponSchemaDto {
    @NonNull
    private String id;
    @NonNull
    private String merchantId;
    private String merchantName;
    private String categoryId;
    private String categoryName;
    private String name;
    private String title;
    private String image;
    private String introduction;
    private Integer commission;
    private Date getStart;
    private Date getEnd;
    private Date validStart;
    private Date validEnd;
    private Integer type;
    private String codeImage;
    private Integer getMethod;

    public static CouponSchemaDto convertFrom(CouponSchema couponSchema) {
        CouponSchemaDto couponSchemaDto = new CouponSchemaDto(couponSchema.getId().toString(), couponSchema.getMerchantId().toString());
        BeanUtils.copyProperties(couponSchema, couponSchemaDto);

        final CategoryDto category = CartisanContext.getBean(CategoryService.class).getCategory(couponSchema.getCategoryId());
        couponSchemaDto.setCategoryId(category.getId());
        couponSchemaDto.setCategoryName(category.getName());

        final MerchantDto merchant = CartisanContext.getBean(MerchantService.class).getMerchant(couponSchema.getMerchantId());
        couponSchemaDto.setMerchantId(merchant.getId());
        couponSchemaDto.setMerchantName(merchant.getName());

        return couponSchemaDto;
    }
}
