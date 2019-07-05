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
    private Long id;

    @Column(name = "merchantId")
    private Long merchantId;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "commission")
    private Integer commission;

    @Column(name = "get_start")
    private Date getStart;

    @Column(name = "get_end")
    private Date getEnd;

    @Column(name = "valid_start")
    private Date validStart;

    @Column(name = "valid_end")
    private Date validEnd;

    @Column(name = "type")
    private Integer type;

    @Column(name = "code_image")
    private String codeImage;

    @Column(name = "getMethod")
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

    private CouponSchema() {
    }
}
