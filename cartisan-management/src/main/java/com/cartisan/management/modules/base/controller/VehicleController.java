package com.cartisan.management.modules.base.controller;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.common.response.StatusCode;
import com.cartisan.management.modules.base.dto.VehicleDto;
import com.cartisan.management.modules.base.gateway.VehicleClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.fail;
import static com.cartisan.common.response.ResponseUtils.success;

/**
 * @author colin
 */
@Api(tags = "车型")
@RestController
@RequestMapping("/vehicle")
@DefaultProperties(defaultFallback = "defaultFallback")
public class VehicleController {
    @Autowired
    private VehicleClient vehicleClient;

    @ApiOperation(value = "获取指定国家下的车型", notes = "获取指定国家下的车型")
    @GetMapping
    @HystrixCommand(fallbackMethod = "fallback")
//    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
//            /** 超时配置 */
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
//            /** 超时熔断 */
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreacker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//
//    })
    public GenericResponse<List<VehicleDto>> findVehicles(
            @ApiParam(value = "国家", required = true) @RequestParam Long countryId) {
        return success(vehicleClient.findVehicles(countryId).getData());
    }

    private GenericResponse<List<VehicleDto>> fallback(Long countryId) {
        return fail();
    }

    @ApiOperation(value = "添加车型", notes = "添加车型")
    @PostMapping
    public GenericResponse addVehicle(
            @ApiParam(value = "车型信息", required = true) @RequestBody VehicleDto vehicleDto) {
        vehicleClient.addVehicle(vehicleDto);

        return new GenericResponse(true, StatusCode.OK, "添加成功");
    }

    @ApiOperation(value = "更新车型", notes = "更新车型")
    @PutMapping("/{id}")
    public GenericResponse editVehicle(
            @ApiParam(value = "车型Id", required = true) @PathVariable Long id,
            @ApiParam(value = "车型信息", required = true) @RequestBody VehicleDto vehicleDto) {
        vehicleClient.editVehicle(id, vehicleDto);

        return new GenericResponse(true, StatusCode.OK, "更新成功");
    }

    @ApiOperation(value = "删除车型", notes = "删除车型")
    @DeleteMapping("/{id}")
    public GenericResponse removeVehicle(
            @ApiParam(value = "车型Id", required = true) @PathVariable long id) {
        vehicleClient.removeVehicle(id);

        return new GenericResponse(true, StatusCode.OK, "删除成功");
    }

    private GenericResponse defaultFallback() {
        return fail("服务异常");
    }
}
