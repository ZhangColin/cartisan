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
@Table(name = "cpn_stores")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Store extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "merchantId")
    private Long merchantId;

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "phone")
    @Setter
    private String phone;

    @Column(name = "area")
    @Setter
    private String area;

    @Column(name = "address")
    @Setter
    private String address;

    @Column(name = "description")
    @Setter
    private String description;

    @Column(name = "sort")
    @Setter
    private Integer sort;

    private Store() {
    }

    public Store(Long id, Long merchantId, String name) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
    }
}
