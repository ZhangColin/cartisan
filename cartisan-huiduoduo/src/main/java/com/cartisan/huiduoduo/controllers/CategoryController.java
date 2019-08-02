package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.CategoryDto;
import com.cartisan.huiduoduo.params.CategoryParam;
import com.cartisan.huiduoduo.services.CategoryService;
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
    public GenericResponse<CategoryDto> addCategory(
            @ApiParam(value = "分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        return success(service.addCategory(categoryParam));
    }

    @ApiOperation(value = "更新分类")
    @PutMapping("/{id}")
    public GenericResponse<CategoryDto> editCategory(
            @ApiParam(value = "分类Id", required = true) @PathVariable Long id,
            @ApiParam(value = "分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        return success(service.editCategory(id, categoryParam));
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/{id}")
    public GenericResponse removeCategory(
            @ApiParam(value = "分类Id", required = true) @PathVariable long id) {
        service.removeCategory(id);

        return success();
    }
}
