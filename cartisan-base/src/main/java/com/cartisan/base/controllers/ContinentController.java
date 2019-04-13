package com.cartisan.base.controllers;

import com.cartisan.base.dtos.ContinentDto;
import com.cartisan.base.services.ContinentService;
import com.cartisan.common.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @GetMapping
    public GenericResponse<List<ContinentDto>> findContinents() {
        return success(continentService.findContinents());
    }
}
