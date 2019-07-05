package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.StoreDto;
import com.cartisan.huiduoduo.params.StoreParam;
import com.cartisan.huiduoduo.services.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@Api(tags = "门店接口")
@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService service;

    @ApiOperation(value = "获取商家所有门店")
    @GetMapping
    public GenericResponse<List<StoreDto>> getStores(Long merchantId){
        return success(service.getStores(merchantId));
    }

    @ApiOperation(value = "添加门店")
    @PostMapping
    public GenericResponse addStore(
            @ApiParam(value = "门店信息", required = true) @Validated @RequestBody StoreParam storeParam) {
        service.addStore(storeParam);

        return success();
    }

    @ApiOperation(value = "更新门店")
    @PutMapping("/{id}")
    public GenericResponse editStore(
            @ApiParam(value = "门店Id", required = true) @PathVariable Long id,
            @ApiParam(value = "门店信息", required = true) @Validated @RequestBody StoreParam storeParam) {
        service.editStore(id, storeParam);

        return success();
    }

    @ApiOperation(value = "删除门店")
    @DeleteMapping("/{id}")
    public GenericResponse removeStore(
            @ApiParam(value = "门店Id", required = true) @PathVariable long id) {
        service.removeStore(id);

        return success();
    }
}
