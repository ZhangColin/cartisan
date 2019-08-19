package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.WeiXinUser;
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

    public static WeiXinUserDto convertFrom(WeiXinUser weixinUser) {
        WeiXinUserDto weixinUserDto = new WeiXinUserDto();
        BeanUtils.copyProperties(weixinUser, weixinUserDto);

        weixinUserDto.setId(weixinUser.getId().toString());
        return weixinUserDto;
    }
}
