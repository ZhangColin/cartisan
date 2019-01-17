package com.cartisan.base.controllers;

import com.cartisan.base.dtos.VehicleDto;
import com.cartisan.base.services.VehicleService;
import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: CityController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public Result<List<VehicleDto>> findVehicles(Long countryId) {
        return new Result<>(true, StatusCode.OK, "查询成功", vehicleService.findVehicles(countryId));
    }

}

