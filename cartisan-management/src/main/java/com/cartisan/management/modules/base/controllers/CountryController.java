package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.management.modules.base.dtos.CountryDto;
import com.cartisan.management.modules.base.clients.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryClient countryClient;

    @GetMapping
    public GenericResponse<List<CountryDto>> findCountries(
            @RequestParam Long continentId) {

        return success(countryClient.findCountries(continentId));
    }
    @GetMapping("/search")
    public GenericResponse<List<CountryDto>> searchCountries(
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false) String name) {

        return success(countryClient.searchCountries(continentId, name));
    }
}
