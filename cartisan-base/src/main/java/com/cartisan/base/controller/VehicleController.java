package com.cartisan.base.controller;

import com.cartisan.base.dto.VehicleDto;
import com.cartisan.base.service.VehicleService;
import com.cartisan.common.response.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.success;

/**
 * @author colin
 */
@Api(tags = "车型")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ApiOperation(value = "获取指定国家下的车型")
    @GetMapping
    public GenericResponse<List<VehicleDto>> findVehicles(
            @ApiParam(value = "国家", required = true) @RequestParam Long countryId) {
        return success(vehicleService.findVehicles(countryId));
    }

    @ApiOperation(value = "添加车型", notes = "添加车型")
    @PostMapping
    public GenericResponse addVehicle(
            @ApiParam(value = "车型信息", required = true) @RequestBody VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);

        return success();
    }

    @ApiOperation(value = "更新车型", notes = "更新车型")
    @PutMapping("/{id}")
    public GenericResponse editVehicle(
            @ApiParam(value = "车型Id", required = true) @PathVariable Long id,
            @ApiParam(value = "车型信息", required = true) @RequestBody VehicleDto vehicleDto) {
        vehicleService.editVehicle(id, vehicleDto);

        return success();
    }

    @ApiOperation(value = "删除车型", notes = "删除车型")
    @DeleteMapping("/{id}")
    public GenericResponse removeVehicle(
            @ApiParam(value = "车型Id", required = true) @PathVariable long id) {
        vehicleService.removeVehicle(id);

        return success();
    }

}

