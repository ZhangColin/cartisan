package com.cartisan.huiduoduo.params;

import com.cartisan.huiduoduo.domains.CouponSchema;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author colin
 */
@Data
public class CouponSchemaSearchParam {
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

    @ApiModelProperty(value = "类型（1、公码；2、私码）", required = true)
    private Integer type;

    public Specification<CouponSchema> getSpecifications() {
        return  (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtils.isBlank(this.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + this.getName() + "%"));
            }

            if (!StringUtils.isBlank(this.getTitle())) {
                predicateList.add(criteriaBuilder.like(root.get("title"), "%" + this.getTitle() + "%"));
            }

            if (this.getCategoryId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("categoryId"), this.getCategoryId()));
            }

            if (this.getMerchantId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("merchantId"), this.getMerchantId()));
            }

            if (this.getType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("type"), this.getType()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
