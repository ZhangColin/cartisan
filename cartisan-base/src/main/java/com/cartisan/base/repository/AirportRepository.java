package com.cartisan.base.repository;

import com.cartisan.base.domain.Airport;
import com.cartisan.common.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author colin
 */
public interface AirportRepository extends BaseRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);

    Page<Airport> findByCityIdIn(Long cityIds[], Pageable pageable);
}
