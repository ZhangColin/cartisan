package com.cartisan.base.controllers;

import com.cartisan.base.dtos.CityDto;
import com.cartisan.base.services.CityService;
import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author colin
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public Result<List<CityDto>> findCities(@RequestParam Long countryId) {
        return new Result<>(true, StatusCode.OK, "查询成功", cityService.findCities(countryId));
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public Result<PageResult<CityDto>> searchAirports(
            @RequestParam(required = false) Long[] countryIds,
            @RequestParam(required = false) String name,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                cityService.searchCities(countryIds, name, currentPage, pageSize));
    }
}

