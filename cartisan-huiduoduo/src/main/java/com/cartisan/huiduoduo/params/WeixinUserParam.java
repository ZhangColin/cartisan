package com.cartisan.huiduoduo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class WeixinUserParam {
    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "门店名称不能为空")
    private String nickName;

    @ApiModelProperty(value = "openId", required = true)
    private String openId;

    @ApiModelProperty(value = "unionId", required = true)
    private String unionId;

    @ApiModelProperty(value = "国家", required = true)
    private String country;

    @ApiModelProperty(value = "城市", required = true)
    private String city;

    @ApiModelProperty(value = "性别", required = true)
    private Integer gender;

    @ApiModelProperty(value = "微信头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "推荐人")
    private Long referrerId;
}
