package com.cartisan.joey.coupon.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.joey.coupon.dtos.CategoryDto;
import com.cartisan.joey.coupon.params.CategoryParam;
import com.cartisan.joey.coupon.services.CategoryService;
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
@Api(tags = "分类接口")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @ApiOperation(value = "获取所有分类")
    @GetMapping
    public GenericResponse<List<CategoryDto>> getAllCategories(){
        return success(service.getCategories());
    }

    @ApiOperation(value = "添加分类")
    @PostMapping
    public GenericResponse addCategory(
            @ApiParam(value = "分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        service.addCategory(categoryParam);

        return success();
    }

    @ApiOperation(value = "更新分类")
    @PutMapping("/{id}")
    public GenericResponse editCategory(
            @ApiParam(value = "分类Id", required = true) @PathVariable Long id,
            @ApiParam(value = "分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        service.editCategory(id, categoryParam);

        return success();
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/{id}")
    public GenericResponse removeCategory(
            @ApiParam(value = "分类Id", required = true) @PathVariable long id) {
        service.removeCategory(id);

        return success();
    }
}
