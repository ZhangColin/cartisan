package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.VehicleDto;
import com.cartisan.management.modules.base.gateways.VehicleClient;
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
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleClient vehicleClient;

    @GetMapping
    public Result<List<VehicleDto>> findVehicles(Long countryId) {

        return new Result<>(true, StatusCode.OK, "查询成功",
                vehicleClient.findVehicles(countryId).getData());
    }

}
