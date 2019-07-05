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
@Api(tags = "推荐人接口")
@RestController
@RequestMapping("/referrers")
public class ReferrerController {
    @Autowired
    private MerchantService service;

    @ApiOperation(value = "获取所有推荐人")
    @GetMapping
    public GenericResponse<List<MerchantDto>> getMerchants(){
        return success(service.getMerchants());
    }

    @ApiOperation(value = "添加推荐人")
    @PostMapping
    public GenericResponse addMerchant(
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody MerchantParam referrerParam) {
        service.addMerchant(referrerParam);

        return success();
    }

    @ApiOperation(value = "更新推荐人")
    @PutMapping("/{id}")
    public GenericResponse editMerchant(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable Long id,
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody MerchantParam referrerParam) {
        service.editMerchant(id, referrerParam);

        return success();
    }

    @ApiOperation(value = "删除推荐人")
    @DeleteMapping("/{id}")
    public GenericResponse removeMerchant(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable long id) {
        service.removeMerchant(id);

        return success();
    }
}
