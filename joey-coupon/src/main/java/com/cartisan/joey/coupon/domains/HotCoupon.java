package com.cartisan.joey.coupon.domains;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "cpn_coupon_hots")
@Getter
@EqualsAndHashCode(callSuper = true)
public class HotCoupon extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_schema_id")
    private Long couponSchemaId;

    @Column(name = "sort")
    @Setter
    private Integer sort;

    private HotCoupon() {
    }

    public HotCoupon(Long couponSchemaId, Integer sort) {
        this.couponSchemaId = couponSchemaId;
        this.sort = sort;
    }
}
