package com.cartisan.management.modules.base.controller;

import com.cartisan.common.dto.PageResult;
import com.cartisan.common.response.GenericResponse;
import com.cartisan.management.modules.base.dto.CityDto;
import com.cartisan.management.modules.base.dto.CountryDto;
import com.cartisan.management.modules.base.gateway.CityClient;
import com.cartisan.management.modules.base.gateway.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.GenericResponse.success;
import static java.util.stream.Collectors.toList;

/**
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
    public GenericResponse<List<CityDto>> findCities(@RequestParam Long countryId) {
        return success(cityClient.findCities(countryId));
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<CityDto>> searchCities(
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

        return success(cityClient.searchCities(countryIds, name, currentPage, pageSize));
    }
}
