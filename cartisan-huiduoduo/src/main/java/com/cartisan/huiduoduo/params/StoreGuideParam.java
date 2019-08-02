package com.cartisan.huiduoduo.params;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
public class StoreGuideParam extends AbstractEntity implements AggregateRoot {
    @ApiModelProperty(value = "门店Id", required = true)
    private Long storeId;

    @ApiModelProperty(value = "门店指引", required = true)
    @NotBlank(message = "门店指引不能为空")
    private String guide;

}
