package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.VehicleDto;
import com.cartisan.management.modules.base.gateways.VehicleClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//
//    })
    public Result<List<VehicleDto>> findVehicles(Long countryId) {
        return new Result<>(true, StatusCode.OK, "查询成功",
                vehicleClient.findVehicles(countryId).getData());
    }

    private Result<List<VehicleDto>> fallback(Long countryId) {
        return new Result(false, StatusCode.ERROR, "查询失败");
    }

    @PostMapping
    public Result addVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleClient.addVehicle(vehicleDto);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public Result editVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        vehicleClient.editVehicle(id, vehicleDto);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{id}")
    public Result removeVehicle(@PathVariable long id) {
        vehicleClient.removeVehicle(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    private Result defaultFallback() {
        return new Result(false, StatusCode.ERROR, "服务异常");
    }
}
