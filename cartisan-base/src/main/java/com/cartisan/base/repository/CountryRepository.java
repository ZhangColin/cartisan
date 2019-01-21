package com.cartisan.base.repository;

import com.cartisan.base.domain.Country;
import com.cartisan.common.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface CountryRepository extends BaseRepository<Country, Long> {
    List<Country> findByContinentId(Long continentId);
}
