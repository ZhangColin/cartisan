package com.cartisan.base.dtos;

import com.cartisan.base.domains.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: Label</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
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

    public static VehicleDto convertFrom(Vehicle vehicle) {
        return new VehicleDto(vehicle.getId(), vehicle.getCode(),
                vehicle.getName(), vehicle.getDescription(),
                vehicle.getPassengers(), vehicle.getPassengersDescription(),
                vehicle.getBigLuggage(), vehicle.getSmallLuggage(),
                vehicle.getPictureUrl(),
                vehicle.getCountryId(), vehicle.getCountryName());
    }
}
