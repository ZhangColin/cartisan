package com.cartisan.management.modules.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class VehicleDto{
    @ApiModelProperty(value = "车型Id", required = true)
    private Long id;
    @ApiModelProperty(value = "车型编号", required = true)
    private String code;
    @ApiModelProperty(value = "车型名称", required = true)
    private String name;
    @ApiModelProperty(value = "车型描述", required = true)
    private String description;
    @ApiModelProperty(value = "乘座人数", required = true)
    private Integer passengers;
    @ApiModelProperty(value = "空间描述", required = true)
    private String passengersDescription;
    @ApiModelProperty(value = "大行李数", required = true)
    private Integer bigLuggage;
    @ApiModelProperty(value = "小行李数", required = true)
    private Integer smallLuggage;
    @ApiModelProperty(value = "图片")
    private String pictureUrl;
    @ApiModelProperty(value = "国家Id", required = true)
    private Long countryId;
    @ApiModelProperty(value = "国家", required = true)
    private String countryName;
}
