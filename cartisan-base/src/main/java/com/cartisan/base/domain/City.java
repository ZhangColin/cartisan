package com.cartisan.base.domain;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "bas_cities")
@Where(clause = "active=1 and deleted=0")
@Data
public class City extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "english_name")
    private String englishName;
    @Column(name = "full_pin_yin")
    private String fullPinYin;
    @Column(name = "simple_pin_yin")
    private String simplePinYin;
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

}
