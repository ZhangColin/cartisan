package com.cartisan.base.domains;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Entity
@Table(name = "bas_vehicles")
@Data
public class Vehicle extends AbstractEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "passengers")
    private Integer passengers;
    @Column(name = "passengers_description")
    private String passengersDescription;
    @Column(name = "big_luggage")
    private Integer bigLuggage;
    @Column(name = "small_luggage")
    private Integer smallLuggage;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "country_name")
    private String countryName;
}
