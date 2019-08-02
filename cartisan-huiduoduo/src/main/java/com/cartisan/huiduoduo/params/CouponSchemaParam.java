package com.cartisan.huiduoduo.params;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author colin
 */
@Data
public class CouponSchemaParam extends AbstractEntity implements AggregateRoot {
    @ApiModelProperty(value = "商户Id", required = true)
    private Long merchantId;

    @ApiModelProperty(value = "分类Id", required = true)
    private Long categoryId;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    @Length(min = 2, max = 32, message = "名称必须在 2 至 32 之间")
    private String name;

    @ApiModelProperty(value = "标题", required = true)
    @NotBlank(message = "标题不能为空")
    @Length(min = 2, max = 32, message = "标题必须在 2 至 32 之间")
    private String title;

    @ApiModelProperty(value = "图片", required = true)
    private String image;

    @ApiModelProperty(value = "基本介绍", required = true)
    private String introduction;

    @ApiModelProperty(value = "佣金", required = true)
    private Integer commission;

    @ApiModelProperty(value = "开始领取时间", required = true)
    private Date getStart;

    @ApiModelProperty(value = "结束领取时间", required = true)
    private Date getEnd;

    @ApiModelProperty(value = "有效开始时间", required = true)
    private Date validStart;

    @ApiModelProperty(value = "有效结束时间", required = true)
    private Date validEnd;

    @ApiModelProperty(value = "类型（1、公码；2、私码）", required = true)
    private Integer type;

    @ApiModelProperty(value = "条形码/二维码图片", required = true)
    private String codeImage;

    @ApiModelProperty(value = "领取方式（1、一人一张；2、使用后再领）", required = true)
    private Integer getMethod;
}
