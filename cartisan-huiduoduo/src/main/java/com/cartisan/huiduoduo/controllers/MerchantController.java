package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.MerchantDto;
import com.cartisan.huiduoduo.params.MerchantParam;
import com.cartisan.huiduoduo.services.MerchantService;
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
@Api(tags = "商家接口")
@RestController
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private MerchantService service;

    @ApiOperation(value = "获取所有商家")
    @GetMapping
    public GenericResponse<List<MerchantDto>> getMerchants(){
        return success(service.getMerchants());
    }

    @ApiOperation(value = "添加商家")
    @PostMapping
    public GenericResponse addMerchant(
            @ApiParam(value = "商家信息", required = true) @Validated @RequestBody MerchantParam merchantParam) {
        service.addMerchant(merchantParam);

        return success();
    }

    @ApiOperation(value = "更新商家")
    @PutMapping("/{id}")
    public GenericResponse editMerchant(
            @ApiParam(value = "商家Id", required = true) @PathVariable Long id,
            @ApiParam(value = "商家信息", required = true) @Validated @RequestBody MerchantParam merchantParam) {
        service.editMerchant(id, merchantParam);

        return success();
    }

    @ApiOperation(value = "删除商家")
    @DeleteMapping("/{id}")
    public GenericResponse removeMerchant(
            @ApiParam(value = "商家Id", required = true) @PathVariable long id) {
        service.removeMerchant(id);

        return success();
    }
}
