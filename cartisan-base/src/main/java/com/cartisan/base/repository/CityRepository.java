package com.cartisan.base.repository;

import com.cartisan.base.domain.City;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findByCountryId(Long countryId);
}
