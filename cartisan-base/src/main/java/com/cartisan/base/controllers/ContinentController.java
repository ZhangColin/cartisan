package com.cartisan.base.controllers;

import com.cartisan.base.dtos.ContinentDto;
import com.cartisan.base.services.ContinentService;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author colin
 */
@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @GetMapping
    public Result<List<ContinentDto>> findContinents() {
        return new Result<>(true, StatusCode.OK, "查询成功",
                continentService.findContinents());
    }
}
