package com.cartisan.goods.domain;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "goods_product_category_attribute_relations")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductCategoryAttributeRelation extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "attribute_id")
    private Long attributeId;

    private ProductCategoryAttributeRelation() {
    }

    public ProductCategoryAttributeRelation(Long categoryId, Long attributeId) {
        this.categoryId = categoryId;
        this.attributeId = attributeId;
    }
}
