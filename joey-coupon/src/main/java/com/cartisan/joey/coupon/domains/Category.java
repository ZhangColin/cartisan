package com.cartisan.joey.coupon.domains;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author colin
 */
@Entity
@Table(name = "cpn_categories")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "sort")
    @Setter
    private Integer sort;

    private Category() {
    }

    public Category(Long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.sort = 0;
    }

    public void changeInfo(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
