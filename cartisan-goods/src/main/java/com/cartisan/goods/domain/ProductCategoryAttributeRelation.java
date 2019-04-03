package com.cartisan.goods.domain;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "goods_product_category_attribute_relations")
@Where(clause = "active=1 and deleted=0")
@Data
public class ProductCategoryAttributeRelation extends AbstractEntity {
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
