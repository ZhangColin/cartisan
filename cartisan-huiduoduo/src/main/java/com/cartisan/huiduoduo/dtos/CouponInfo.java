package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.CouponSchema;
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
public class CouponInfo {
    @NonNull
    private String id;
    @NonNull
    private String merchantId;
    private String categoryId;
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

    public static CouponInfo convertFrom(CouponSchema couponSchema) {
        CouponInfo couponSchemaDto = new CouponInfo(couponSchema.getId().toString(), couponSchema.getMerchantId().toString());
        BeanUtils.copyProperties(couponSchema, couponSchemaDto);
        couponSchemaDto.setCategoryId(couponSchema.getCategoryId().toString());

        return couponSchemaDto;
    }
}
