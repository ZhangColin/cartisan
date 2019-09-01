package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Referrer;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author colin
 */
@Data
@RequiredArgsConstructor
public class ReferrerDto {
    @NonNull
    private String id;

    @NonNull
    private String userId;

    @NonNull
    private String name;

    private String phone;

    private String profession;

    private String debitCart;

    private String bank;

    private Integer level;

    private Integer auditStatus;

    public static ReferrerDto convertFrom(Referrer referrer) {
        ReferrerDto referrerDto = new ReferrerDto(referrer.getId().toString(), referrer.getUserId().toString(), referrer.getName());
        BeanUtils.copyProperties(referrer, referrerDto);

        return referrerDto;
    }
}
