package com.cartisan.base.controller;

import com.cartisan.base.dto.CityDto;
import com.cartisan.base.service.CityService;
import com.cartisan.common.dto.PageResult;
import com.cartisan.common.response.GenericResponse;
import com.cartisan.common.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.success;

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
        return new GenericResponse<>(true, StatusCode.OK, "查询成功", cityService.findCities(countryId));
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

