package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.MerchantCoupon;
import com.cartisan.huiduoduo.services.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@Api(tags = "优惠券接口")
@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    private CouponService service;

//    @ApiOperation(value = "搜索优惠券")
//    @GetMapping("/search/{currentPage}/{pageSize}")
//    public GenericResponse<PageResult<CouponSchemaDto>> searchCoupons(
//            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
//            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
//        return success(service.searchCoupons( currentPage, pageSize));
//    }





    @ApiOperation(value = "获取可领取优惠券")
    @GetMapping("/canGet")
    public GenericResponse<List<MerchantCoupon>> getCouponSchemas(){
        return success(service.getCanGetCoupons());
    }


//    @ApiOperation(value = "获取所有优惠券模板")
//    @GetMapping
//    public GenericResponse<List<CouponSchemaDto>> getCouponSchemas(){
//        return success(service.getCouponSchemas());
//    }
//
//    @ApiOperation(value = "获取优惠券模板")
//    @GetMapping("/{id}")
//    public GenericResponse<CouponSchemaDto> getCouponSchema(
//            @ApiParam(value = "品牌Id", required = true) @PathVariable Long id) {
//        return success(service.getCouponSchema(id));
//    }
//
//    @ApiOperation(value = "添加优惠券模板")
//    @PostMapping
//    public GenericResponse addCouponSchema(
//            @ApiParam(value = "优惠券模板信息", required = true) @Validated @RequestBody CouponSchemaParam couponSchemaParam) {
//        service.addCouponSchema(couponSchemaParam);
//
//        return success();
//    }
//
//    @ApiOperation(value = "更新优惠券模板")
//    @PutMapping("/{id}")
//    public GenericResponse editCouponSchema(
//            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable Long id,
//            @ApiParam(value = "优惠券模板信息", required = true) @Validated @RequestBody CouponSchemaParam couponSchemaParam) {
//        service.editCouponSchema(id, couponSchemaParam);
//
//        return success();
//    }
//
//    @ApiOperation(value = "删除优惠券模板")
//    @DeleteMapping("/{id}")
//    public GenericResponse removeCouponSchema(
//            @ApiParam(value = "优惠券模板Id", required = true) @PathVariable long id) {
//        service.removeCouponSchema(id);
//
//        return success();
//    }
}
