package com.cartisan.huiduoduo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class StoreParam {
    @ApiModelProperty(value = "门店名称", required = true)
    @NotBlank(message = "门店名称不能为空")
    @Length(min = 2, max = 32, message = "门店名称必须在 2 至 32 之间")
    private String name;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "所属区域")
    private String area;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "描述（门店指引）")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
