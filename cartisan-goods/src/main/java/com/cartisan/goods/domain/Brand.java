package com.cartisan.goods.domain;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "goods_brands")
@Where(clause = "active=1 and deleted=0")
@Data
public class Brand extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "first_letter")
    private String firstLetter;
    @Column(name = "is_manufacturer")
    private Boolean isManufacturer;
    @Column(name = "is_show")
    private Boolean isShow;
    @Column(name = "logo")
    private String logo;
    @Column(name = "big_pic")
    private String bigPic;
    @Column(name = "sort")
    private Integer sort;
}
