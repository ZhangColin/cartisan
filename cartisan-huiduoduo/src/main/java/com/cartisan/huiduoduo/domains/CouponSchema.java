package com.cartisan.huiduoduo.domains;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author colin
 */
@Entity
@Table(name = "cpn_coupon_schemas")
@Getter
@EqualsAndHashCode(callSuper = true)
public class CouponSchema extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    @Setter
    private Long id;

    @Column(name = "merchant_id")
    @Setter
    private Long merchantId;

    @Column(name = "category_id")
    @Setter
    private Long categoryId;

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "title")
    @Setter
    private String title;

    @Column(name = "image")
    @Setter
    private String image;

    @Column(name = "introduction")
    @Setter
    private String introduction;

    @Column(name = "commission")
    @Setter
    private Integer commission;

    @Column(name = "get_start")
    @Setter
    private Date getStart;

    @Column(name = "get_end")
    @Setter
    private Date getEnd;

    @Column(name = "valid_start")
    @Setter
    private Date validStart;

    @Column(name = "valid_end")
    @Setter
    private Date validEnd;

    @Column(name = "type")
    @Setter
    private Integer type;

    @Column(name = "code_image")
    @Setter
    private String codeImage;

    @Column(name = "getMethod")
    @Setter
    private Integer getMethod;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "coupon_schema_id")
    private List<StoreGuide> storeGuides = new ArrayList<>();

    @Column(name = "operator")
    @Setter
    private String operator;
    @Column(name = "operate_ip")
    @Setter
    private String operateIp;
}
