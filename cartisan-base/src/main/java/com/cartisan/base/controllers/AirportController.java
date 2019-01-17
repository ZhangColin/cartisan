package com.cartisan.base.controllers;

import com.cartisan.base.dtos.AirportDto;
import com.cartisan.base.services.AirportService;
import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: CityController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportDto> findAirports(Long cityId) {
        return airportService.findAirports(cityId);
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public Result<PageResult<AirportDto>> searchAirports(
            @RequestParam(required = false) Long[] cityIds,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                airportService.searchAirports(cityIds, currentPage, pageSize));
    }

}

