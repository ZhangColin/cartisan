package com.cartisan.huiduoduo.dtos;

import com.cartisan.common.CartisanContext;
import com.cartisan.huiduoduo.domains.WeiXinUser;
import com.cartisan.huiduoduo.services.ReferrerService;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author colin
 */
@Data
public class WeiXinUserDto {
    private String id;

    private String nickName;

    private String openId;

    private String unionId;

    private String language;

    private String country;

    private String province;

    private String city;

    private Integer gender;

    private String avatarUrl;

    private String referrerId;

    private Boolean isReferrer;

    public static WeiXinUserDto convertFrom(WeiXinUser weixinUser) {
        WeiXinUserDto weixinUserDto = new WeiXinUserDto();
        BeanUtils.copyProperties(weixinUser, weixinUserDto);

        weixinUserDto.setId(weixinUser.getId().toString());
        if(weixinUser.getReferrerId()!=null) {
            weixinUserDto.setReferrerId(weixinUser.getReferrerId().toString());
        }

        final ReferrerService referrerService = CartisanContext.getBean(ReferrerService.class);
        weixinUserDto.isReferrer = referrerService.isReferrer(weixinUser.getId());

        return weixinUserDto;
    }
}
