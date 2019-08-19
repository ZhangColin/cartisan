package com.cartisan.huiduoduo.domains;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author colin
 */
@Entity
@Table(name = "cpn_coupons")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Coupon extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "coupon_schema_id")
    private Long couponSchemaId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "code_image")
    private String codeImage;

    @Column(name = "get_date")
    private Date getDate;

    @Column(name = "use_date")
    private Date useDate;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "valid_start")
    private Date validStart;

    @Column(name = "valid_end")
    private Date validEnd;

    @Column(name = "status")
    private Integer status;

    private Coupon() {
    }

    public Coupon(Long id, Long userId, CouponSchema couponSchema) {
        this.id = id;
        this.userId = userId;

        this.couponSchemaId = couponSchema.getId();

        // 优惠券码以后应改成通过 schema 指定的规则生成或由 schema 提供
        this.couponCode = id.toString();

        this.codeImage = couponSchema.getCodeImage();
        this.getDate = new Date();

        this.validStart = couponSchema.getValidStart();
        this.validEnd = couponSchema.getValidEnd();

        this.status = 0;
    }
}
