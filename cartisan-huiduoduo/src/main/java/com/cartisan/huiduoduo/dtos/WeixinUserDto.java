package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.WeixinUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author colin
 */
@Data
public class WeixinUserDto {
    private String id;

    private String nickName;

    private String openId;

    private String unionId;

    private String country;

    private String city;

    private Integer gender;

    private String avatarUrl;

    private String referrerId;

    public static WeixinUserDto convertFrom(WeixinUser weixinUser) {
        WeixinUserDto weixinUserDto = new WeixinUserDto();
        BeanUtils.copyProperties(weixinUser, weixinUserDto);

        return weixinUserDto;
    }
}
