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
    @ApiModelProperty(value = "商家名称", required = true)
    @NotBlank(message = "商家名称不能为空")
    @Length(min = 2, max = 32, message = "商家名称必须在 2 至 32 之间")
    private String name;

    @ApiModelProperty(value = "商家Logo", required = true)
    @NotBlank(message = "商家Logo不能为空")
    private String logo;
}
