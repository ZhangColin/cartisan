package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.CountryDto;
import com.cartisan.management.modules.base.gateways.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: ContinentController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryClient countryClient;

    @GetMapping
    public Result<List<CountryDto>> findCountries(
            @RequestParam Long continentId) {

        return new Result<>(true, StatusCode.OK, "查询成功",
                countryClient.findCountries(continentId));
    }
    @GetMapping("/search")
    public Result<List<CountryDto>> searchCountries(
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false) String name) {

        return new Result<>(true, StatusCode.OK, "查询成功",
                countryClient.searchCountries(continentId, name));
    }
}
