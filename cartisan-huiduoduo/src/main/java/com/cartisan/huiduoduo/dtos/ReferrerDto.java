package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Referrer;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author colin
 */
@Data
public class ReferrerDto {
    private Long id;

    private Long userId;

    private String phone;

    private String profession;

    private String debitCart;

    private String bank;

    private Integer level;

    public static ReferrerDto convertFrom(Referrer referrer) {
        ReferrerDto referrerDto = new ReferrerDto();
        BeanUtils.copyProperties(referrer, referrerDto);

        return referrerDto;
    }
}
