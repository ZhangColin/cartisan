package com.cartisan.base.controller;

import com.cartisan.base.dto.CityDto;
import com.cartisan.base.service.CityService;
import com.cartisan.common.dto.PageResult;
import com.cartisan.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.GenericResponse.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public GenericResponse<List<CityDto>> findCities(@RequestParam Long countryId) {
        return success(cityService.findCities(countryId));
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<CityDto>> searchAirports(
            @RequestParam(required = false) Long[] countryIds,
            @RequestParam(required = false) String name,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return success(cityService.searchCities(countryIds, name, currentPage, pageSize));
    }
}

