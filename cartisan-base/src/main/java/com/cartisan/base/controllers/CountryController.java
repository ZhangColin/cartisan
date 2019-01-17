package com.cartisan.base.controllers;

import com.cartisan.base.dtos.CountryDto;
import com.cartisan.base.services.CountryService;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: CountryController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public Result<List<CountryDto>> findCountries(@RequestParam Long continentId) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                countryService.findCountries(continentId));
    }

    @GetMapping("/search")
    public Result<List<CountryDto>> searchCountries(
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false) String name) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                countryService.searchCountries(continentId, name));
    }
}
