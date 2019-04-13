package com.cartisan.base.services;

import com.cartisan.base.domains.Airport;
import com.cartisan.base.dtos.AirportDto;
import com.cartisan.base.repositories.AirportRepository;
import com.cartisan.common.dtos.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Cacheable(value="cache:base:services:AirportService:findAirports", key="#cityId")
    public List<AirportDto> findAirports(Long cityId) {
        List<Airport> airports = airportRepository.findByCityId(cityId);

        return airports.stream().map(AirportDto::convertFrom).collect(Collectors.toList());

    }

    public PageResult<AirportDto> searchAirports(Long[] cityIds, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<Airport> searchResult =
                cityIds!=null && cityIds.length>0?
                airportRepository.findByCityIdIn(cityIds, pageRequest):
                airportRepository.findAll(pageRequest);

        return new PageResult<>(searchResult.getTotalElements(),searchResult.getTotalPages(),
                searchResult.map(AirportDto::convertFrom).getContent());
    }

}


