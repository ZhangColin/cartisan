package com.cartisan.base.repositories;

import com.cartisan.base.domains.Vehicle;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface VehicleRepository extends BaseRepository<Vehicle, Long> {
    List<Vehicle> findByCountryId(Long countryId);
}
