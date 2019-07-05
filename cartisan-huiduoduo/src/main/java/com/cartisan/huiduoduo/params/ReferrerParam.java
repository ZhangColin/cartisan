package com.cartisan.huiduoduo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class ReferrerParam {
    @ApiModelProperty(value = "用户Id", required = true)
    @NotBlank(message = "用户Id不能为空")
    private Long userId;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "职业")
    private String profession;

    @ApiModelProperty(value = "银行卡号")
    private String debitCart;

    @ApiModelProperty(value = "银行")
    private String bank;
}
