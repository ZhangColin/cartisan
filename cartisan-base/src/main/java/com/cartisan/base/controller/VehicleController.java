package com.cartisan.base.controller;

import com.cartisan.base.dto.VehicleDto;
import com.cartisan.base.service.VehicleService;
import com.cartisan.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public GenericResponse<List<VehicleDto>> findVehicles(Long countryId) {
        return success(vehicleService.findVehicles(countryId));
    }

    @PostMapping
    public GenericResponse addVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);

        return success();
    }

    @PutMapping("/{id}")
    public GenericResponse editVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        vehicleService.editVehicle(id, vehicleDto);

        return success();
    }

    @DeleteMapping("/{id}")
    public GenericResponse removeVehicle(@PathVariable long id) {
        vehicleService.removeVehicle(id);

        return success();
    }

}

