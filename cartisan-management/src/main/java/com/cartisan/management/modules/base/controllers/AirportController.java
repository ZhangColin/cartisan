package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.AirportDto;
import com.cartisan.management.modules.base.dtos.CityDto;
import com.cartisan.management.modules.base.gateways.AirportClient;
import com.cartisan.management.modules.base.gateways.CityClient;
import com.cartisan.management.modules.base.gateways.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>Title: ContinentController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    private AirportClient airportClient;

    @Autowired
    private CityClient cityClient;

    @Autowired
    private CountryClient countryClient;

    @GetMapping
    public List<AirportDto> findAirports(Long cityId) {
        return airportClient.findAirport(cityId);
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public Result<PageResult<AirportDto>> searchAirports(
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Long cityId,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {

        Long[] cityIds = null;
        if (cityId != null) {
            cityIds = new Long[]{cityId};
        } else if (countryId != null) {
            final List<CityDto> cities = cityClient.findCities(countryId);
            cityIds = cities.stream().map(CityDto::getId).collect(toList()).toArray(new Long[cities.size()]);
        } else if (continentId != null) {
            final List<CityDto> cities = countryClient.findCountries(continentId)
                    .stream().flatMap(countryDto -> cityClient.findCities(countryDto.getId()).stream())
                    .collect(Collectors.toList());
            cityIds = cities.stream().map(CityDto::getId).collect(toList()).toArray(new Long[cities.size()]);
        }

        return new Result<>(true, StatusCode.OK, "查询成功",
                airportClient.searchAirports(cityIds, currentPage, pageSize));
    }
}
