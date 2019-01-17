package com.cartisan.base.controllers;

import com.cartisan.base.dtos.VehicleDto;
import com.cartisan.base.services.VehicleService;
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
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public Result<List<VehicleDto>> findVehicles(Long countryId) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                vehicleService.findVehicles(countryId));
    }

    @PostMapping
    public Result addVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public Result editVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        vehicleService.editVehicle(id, vehicleDto);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{id}")
    public Result removeVehicle(@PathVariable long id) {
        vehicleService.removeVehicle(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

}

