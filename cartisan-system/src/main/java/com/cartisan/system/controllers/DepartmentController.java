package com.cartisan.system.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.system.dtos.DepartmentDto;
import com.cartisan.system.dtos.TreeNode;
import com.cartisan.system.params.DepartmentParam;
import com.cartisan.system.queries.DepartmentQuery;
import com.cartisan.system.services.DepartmentService;
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
@Api(tags = "DepartmentController")
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentQuery query;

    @ApiOperation(value = "获取部门列表")
    @GetMapping
    public GenericResponse<List<DepartmentDto>> getDepartmentList() {
        return success(service.getDepartmentList());
    }

    @ApiOperation(value = "获取部门树")
    @GetMapping("/tree")
    public GenericResponse<List<TreeNode>> getDepartmentTree() {
        return success(query.getDepartmentTree());
    }

    @ApiOperation(value = "添加部门")
    @PostMapping
    public GenericResponse addDepartment(
            @ApiParam(value = "部门信息", required = true) @Validated @RequestBody DepartmentParam departmentParam) {
        service.addDepartment(departmentParam);

        return success();
    }

    @ApiOperation(value = "更新部门")
    @PutMapping("/{id}")
    public GenericResponse editDepartment(
            @ApiParam(value = "部门Id", required = true) @PathVariable Long id,
            @ApiParam(value = "部门信息", required = true) @Validated @RequestBody DepartmentParam departmentParam) {
        service.editDepartment(id, departmentParam);

        return success();
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public GenericResponse removeDepartment(
            @ApiParam(value = "部门Id", required = true) @PathVariable long id) {
        service.removeDepartment(id);

        return success();
    }
}
