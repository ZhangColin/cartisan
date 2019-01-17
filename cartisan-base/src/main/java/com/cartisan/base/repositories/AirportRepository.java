package com.cartisan.base.repositories;

import com.cartisan.base.domains.Airport;
import com.cartisan.common.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>Title: AirportRepository</p>
 * <p>Description: </p>
 *
 * @author colin
 */
public interface AirportRepository extends BaseRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);

    Page<Airport> findByCityIdIn(Long cityIds[], Pageable pageable);
}
