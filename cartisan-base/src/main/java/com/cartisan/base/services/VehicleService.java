package com.cartisan.base.services;

import com.cartisan.base.domains.Vehicle;
import com.cartisan.base.dtos.VehicleDto;
import com.cartisan.base.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: CityService</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Cacheable(value="cache:base:services:VehicleService:findVehicles", key="#countryId")
    public List<VehicleDto> findVehicles(Long countryId) {
        List<Vehicle> vehicles;
        if (countryId == null) {
            vehicles = vehicleRepository.findAll();
        } else {
            vehicles = vehicleRepository.findByCountryId(countryId);
        }

        return vehicles.stream().map(VehicleDto::convertFrom).collect(Collectors.toList());
    }

}


