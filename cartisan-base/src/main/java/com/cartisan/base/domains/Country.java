package com.cartisan.base.domains;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: Continent</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Entity
@Table(name = "bas_countries")
@Data
public class Country extends AbstractEntity {
    @Id
    @Column(name = "id")
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
    @Column(name = "continent_id")
    private Long continentId;
    @Column(name = "continent_name")
    private String continentName;
}
