package com.cartisan.management.modules.base.dtos;

import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class VehicleDto{
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer passengers;
    private String passengersDescription;
    private Integer bigLuggage;
    private Integer smallLuggage;
    private String pictureUrl;
    private Long countryId;
    private String countryName;
}
