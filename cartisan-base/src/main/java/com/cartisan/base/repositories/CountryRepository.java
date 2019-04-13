package com.cartisan.base.repositories;

import com.cartisan.base.domains.Country;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface CountryRepository extends BaseRepository<Country, Long> {
    List<Country> findByContinentId(Long continentId);
}
