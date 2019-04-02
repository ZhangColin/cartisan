package com.cartisan.goods.controller;

import com.cartisan.common.dto.PageResult;
import com.cartisan.common.response.GenericResponse;
import com.cartisan.goods.dto.ProductAttributeDto;
import com.cartisan.goods.service.ProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.common.response.ResponseUtils.success;

/**
 * @author colin
 */
@Api(tags = "ProductAttributeController", description = "商品品牌管理")
@RestController
@RequestMapping("/productAttributes")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeService service;

    @ApiOperation(value = "获取商品属性")
    @GetMapping("/{id}")
    public GenericResponse<ProductAttributeDto> getProductAttribute(
            @ApiParam(value = "属性Id", required = true) @PathVariable Long id) {
        return success(service.getProductAttribute(id));
    }

    @ApiOperation(value = "根据分类查询商品属性")
    @GetMapping("/search/{categoryId}/{type}/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<ProductAttributeDto>> searchProductAttributes(
            @PathVariable Long categoryId,
            @PathVariable Integer type,
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize) {
        return success(service.searchProductAttributes(categoryId, type, currentPage, pageSize));
    }

    @ApiOperation(value = "添加商品属性")
    @PostMapping
    public GenericResponse addProductAttribute(
            @ApiParam(value = "商品属性信息", required = true) @RequestBody ProductAttributeDto productAttributeDto) {
        service.addProductAttribute(productAttributeDto);

        return success();
    }

    @ApiOperation(value = "更新商品属性")
    @PutMapping("/{id}")
    public GenericResponse editProductAttribute(
            @ApiParam(value = "商品属性Id", required = true) @PathVariable Long id,
            @ApiParam(value = "商品属性信息", required = true) @RequestBody ProductAttributeDto productAttributeDto) {
        service.editProductAttribute(id, productAttributeDto);

        return success();
    }

    @ApiOperation(value = "删除商品属性")
    @DeleteMapping("/{id}")
    public GenericResponse removeProductAttribute(
            @ApiParam(value = "商品属性Id", required = true) @PathVariable long id) {
        service.removeProductAttribute(id);

        return success();
    }
}
