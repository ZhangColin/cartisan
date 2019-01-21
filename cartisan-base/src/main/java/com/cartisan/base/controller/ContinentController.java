package com.cartisan.base.controller;

import com.cartisan.base.dto.ContinentDto;
import com.cartisan.base.service.ContinentService;
import com.cartisan.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.success;

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
