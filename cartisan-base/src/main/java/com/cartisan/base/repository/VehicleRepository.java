package com.cartisan.base.repository;

import com.cartisan.base.domain.Vehicle;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface VehicleRepository extends BaseRepository<Vehicle, Long> {
    List<Vehicle> findByCountryId(Long countryId);
}
