package com.cartisan.base.controller;

import com.cartisan.base.dto.AirportDto;
import com.cartisan.base.service.AirportService;
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
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportDto> findAirports(@RequestParam Long cityId) {
        return airportService.findAirports(cityId);
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<AirportDto>> searchAirports(
            @RequestParam(required = false) Long[] cityIds,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return success(airportService.searchAirports(cityIds, currentPage, pageSize));
    }

}

