package com.cartisan.goods.domain;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "goods_product_attribute_categories")
@Where(clause = "active=1 and deleted=0")
@Data
public class ProductAttributeCategory extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "specification_count")
    private Integer specificationCount;
    @Column(name = "param_count")
    private Integer paramCount;

    private ProductAttributeCategory() {
    }

    public ProductAttributeCategory(String name) {
        this.name = name;
        this.specificationCount = 0;
        this.paramCount = 0;
    }

    public void specificationCountIncrement() {
        this.specificationCount++;
    }

    public void specificationCountDecrement() {
        this.specificationCount--;
    }

    public void paramCountIncrement() {
        this.paramCount++;
    }

    public void paramCountDecrement() {
        this.paramCount--;
    }
}
