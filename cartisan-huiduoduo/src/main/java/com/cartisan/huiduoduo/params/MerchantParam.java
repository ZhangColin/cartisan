package com.cartisan.huiduoduo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class MerchantParam {
    @ApiModelProperty(value = "商户名称", required = true)
    @NotBlank(message = "商户名称不能为空")
    @Length(min = 2, max = 32, message = "商户名称必须在 2 至 32 之间")
    private String name;

    @ApiModelProperty(value = "商户Logo", required = true)
    @NotBlank(message = "商户Logo不能为空")
    private String logo;
}
