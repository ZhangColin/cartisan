package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.dtos.PageResult;
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
@Api(tags = "商户接口")
@RestController
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private MerchantService service;

    @ApiOperation(value = "搜索商户")
    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<MerchantDto>> searchMerchants(
            @ApiParam(value = "查询商户名") @RequestParam(required = false) String name,
            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
        return success(service.searchMerchants(name, currentPage, pageSize));
    }


    @ApiOperation(value = "获取所有商户")
    @GetMapping
    public GenericResponse<List<MerchantDto>> getMerchants(){
        return success(service.getMerchants());
    }

    @ApiOperation(value = "添加商户")
    @PostMapping
    public GenericResponse<MerchantDto> addMerchant(
            @ApiParam(value = "商户信息", required = true) @Validated @RequestBody MerchantParam merchantParam) {
        return success(service.addMerchant(merchantParam));
    }

    @ApiOperation(value = "更新商户")
    @PutMapping("/{id}")
    public GenericResponse editMerchant(
            @ApiParam(value = "商户Id", required = true) @PathVariable Long id,
            @ApiParam(value = "商户信息", required = true) @Validated @RequestBody MerchantParam merchantParam) {
        return success(service.editMerchant(id, merchantParam));
    }

    @ApiOperation(value = "删除商户")
    @DeleteMapping("/{id}")
    public GenericResponse removeMerchant(
            @ApiParam(value = "商户Id", required = true) @PathVariable long id) {
        service.removeMerchant(id);

        return success();
    }
}
