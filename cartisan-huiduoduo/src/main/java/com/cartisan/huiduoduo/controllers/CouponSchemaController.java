package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.CouponSchemaDto;
import com.cartisan.huiduoduo.dtos.StoreGuideDto;
import com.cartisan.huiduoduo.params.CouponSchemaParam;
import com.cartisan.huiduoduo.params.CouponSchemaSearchParam;
import com.cartisan.huiduoduo.params.StoreGuideParam;
import com.cartisan.huiduoduo.services.CouponSchemaService;
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
@Api(tags = "优惠券模板接口")
@RestController
@RequestMapping("/couponSchemas")
public class CouponSchemaController {
    @Autowired
    private CouponSchemaService service;

    @ApiOperation(value = "搜索优惠券模板")
    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<CouponSchemaDto>> searchCouponSchemas(
            @ApiParam(value = "查询优惠券模板") CouponSchemaSearchParam searchParam,
            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
        return success(service.searchCouponSchemas(searchParam, currentPage, pageSize));
    }

    @ApiOperation(value = "获取所有优惠券模板")
    @GetMapping
    public GenericResponse<List<CouponSchemaDto>> getCouponSchemas(){
        return success(service.getCouponSchemas());
    }

    @ApiOperation(value = "获取优惠券模板")
    @GetMapping("/{id}")
    public GenericResponse<CouponSchemaDto> getCouponSchema(
            @ApiParam(value = "品牌Id", required = true) @PathVariable Long id) {
        return success(service.getCouponSchema(id));
    }

    @ApiOperation(value = "添加优惠券模板")
    @PostMapping
    public GenericResponse addCouponSchema(
            @ApiParam(value = "优惠券模板信息", required = true) @Validated @RequestBody CouponSchemaParam couponSchemaParam) {
        service.addCouponSchema(couponSchemaParam);

        return success();
    }

    @ApiOperation(value = "更新优惠券模板")
    @PutMapping("/{id}")
    public GenericResponse editCouponSchema(
            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable Long id,
            @ApiParam(value = "优惠券模板信息", required = true) @Validated @RequestBody CouponSchemaParam couponSchemaParam) {
        service.editCouponSchema(id, couponSchemaParam);

        return success();
    }

    @ApiOperation(value = "删除优惠券模板")
    @DeleteMapping("/{id}")
    public GenericResponse removeCouponSchema(
            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable long id) {
        service.removeCouponSchema(id);

        return success();
    }

    @ApiOperation(value = "获取门店指南")
    @GetMapping("/{couponSchemaId}/storeGuides")
    public GenericResponse<List<StoreGuideDto>> getStoreGuides(
            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable Long couponSchemaId) {
        return success(service.getStoreGuides(couponSchemaId));
    }

    @ApiOperation(value = "保存门店指引")
    @PutMapping("/{couponSchemaId}/storeGuides")
    public GenericResponse saveStoreGuides(
            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable Long couponSchemaId,
            @ApiParam(value = "优惠券模板信息", required = true) @Validated @RequestBody StoreGuideParam storeGuideParam) {
        service.saveStoreGuid(couponSchemaId, storeGuideParam.getStoreId(), storeGuideParam.getGuide());

        return success();
    }

    @ApiOperation(value = "删除门店指引")
    @DeleteMapping("/{id}/storeGuides")
    public GenericResponse removeStoreGuides(
            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable Long id,
            @ApiParam(value = "门店Id", required = true) @Validated @RequestParam Long storeId) {
        service.removeStoreGuid(id, storeId);

        return success();
    }
}
