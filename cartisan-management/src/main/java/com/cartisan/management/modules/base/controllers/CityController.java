package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.CityDto;
import com.cartisan.management.modules.base.dtos.CountryDto;
import com.cartisan.management.modules.base.gateways.CityClient;
import com.cartisan.management.modules.base.gateways.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>Title: ContinentController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityClient cityClient;

    @Autowired
    private CountryClient countryClient;

    @GetMapping
    public Result<List<CityDto>> findCities(@RequestParam Long countryId) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                cityClient.findCities(countryId));
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public Result<PageResult<CityDto>> searchCities(
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) String name,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        Long[] countryIds = null;
        if (countryId != null) {
            countryIds = new Long[]{countryId};
        } else if (continentId != null) {
            final List<CountryDto> countries = countryClient.findCountries(continentId);
            countryIds = countries
                    .stream().map(CountryDto::getId).collect(toList()).toArray(new Long[countries.size()]);
        }

        return new Result<>(true, StatusCode.OK, "查询成功",
                cityClient.searchCities(countryIds, name, currentPage, pageSize));
    }
}
