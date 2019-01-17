package com.cartisan.base.repositories;

import com.cartisan.base.domains.City;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * <p>Title: LabelRepository</p>
 * <p>Description: </p>
 *
 * @author colin
 */
public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findByCountryId(Long countryId);
}
