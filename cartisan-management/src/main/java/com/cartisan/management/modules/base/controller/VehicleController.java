package com.cartisan.management.modules.base.controller;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.common.response.StatusCode;
import com.cartisan.management.modules.base.dto.VehicleDto;
import com.cartisan.management.modules.base.gateway.VehicleClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.response.ResponseUtils.fail;
import static com.cartisan.common.response.ResponseUtils.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/vehicle")
@DefaultProperties(defaultFallback = "defaultFallback")
public class VehicleController {
    @Autowired
    private VehicleClient vehicleClient;

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
    public GenericResponse<List<VehicleDto>> findVehicles(Long countryId) {
        return success(vehicleClient.findVehicles(countryId).getData());
    }

    private GenericResponse<List<VehicleDto>> fallback(Long countryId) {
        return fail();
    }

    @PostMapping
    public GenericResponse addVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleClient.addVehicle(vehicleDto);

        return new GenericResponse(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public GenericResponse editVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        vehicleClient.editVehicle(id, vehicleDto);

        return new GenericResponse(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{id}")
    public GenericResponse removeVehicle(@PathVariable long id) {
        vehicleClient.removeVehicle(id);

        return new GenericResponse(true, StatusCode.OK, "删除成功");
    }

    private GenericResponse defaultFallback() {
        return fail("服务异常");
    }
}
