package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Coupon;
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
public class CouponDto {
    @NonNull
    private String id;
    @NonNull
    private String couponSchemaId;
    private String billId;
    @NonNull
    private String userId;
    private String couponCode;
    private String codeImage;
    private Date getDate;
    private Date useDate;
    private Date validStart;
    private Date validEnd;
    private Integer amount;
    private Integer status;

    public static CouponDto convertFrom(Coupon coupon) {
        CouponDto couponDto = new CouponDto(coupon.getId().toString(),
                coupon.getCouponSchemaId().toString(), coupon.getUserId().toString());
        BeanUtils.copyProperties(coupon, couponDto);

        return couponDto;
    }
}
