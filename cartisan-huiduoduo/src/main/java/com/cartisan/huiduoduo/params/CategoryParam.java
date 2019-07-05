package com.cartisan.huiduoduo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class CategoryParam {
    @ApiModelProperty(value = "分类名称", required = true)
    @NotBlank(message = "分类名称不能为空")
    @Length(min = 2, max = 32, message = "分类名称必须在 2 至 32 之间")
    private String name;

    @ApiModelProperty(value = "分类图标", required = true)
    @NotBlank(message = "分类图标不能为空")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
