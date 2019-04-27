package com.cartisan.system.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.system.dtos.PermissionDto;
import com.cartisan.system.dtos.TreeNode;
import com.cartisan.system.params.PermissionParam;
import com.cartisan.system.queries.PermissionQuery;
import com.cartisan.system.services.PermissionService;
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
@Api(tags = "PermissionController")
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService service;

    @Autowired
    private PermissionQuery query;

    @ApiOperation(value = "获取菜单/权限列表")
    @GetMapping
    public GenericResponse<List<PermissionDto>> getAllPermissions() {
        return success(service.getPermissionList());
    }

    @ApiOperation(value = "获取菜单/权限树")
    @GetMapping("/tree")
    public GenericResponse<List<TreeNode>> getDepartmentTree() {
        return success(query.getPermissionTree());
    }

    @ApiOperation(value = "添加菜单/权限")
    @PostMapping
    public GenericResponse addPermission(
            @ApiParam(value = "菜单/权限信息", required = true) @Validated @RequestBody PermissionParam permissionParam) {
        service.addPermission(permissionParam);

        return success();
    }

    @ApiOperation(value = "更新菜单/权限")
    @PutMapping("/{id}")
    public GenericResponse editPermission(
            @ApiParam(value = "菜单/权限Id", required = true) @PathVariable Long id,
            @ApiParam(value = "菜单/权限信息", required = true) @Validated @RequestBody PermissionParam permissionParam) {
        service.editPermission(id, permissionParam);

        return success();
    }

    @ApiOperation(value = "删除菜单/权限")
    @DeleteMapping("/{id}")
    public GenericResponse removePermission(
            @ApiParam(value = "菜单/权限Id", required = true) @PathVariable long id) {
        service.removePermission(id);

        return success();
    }
}
