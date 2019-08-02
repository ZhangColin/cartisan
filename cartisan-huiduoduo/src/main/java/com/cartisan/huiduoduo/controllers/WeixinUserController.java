package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.WeixinUserDto;
import com.cartisan.huiduoduo.params.WeixinUserParam;
import com.cartisan.huiduoduo.services.WeixinUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@Api(tags = "微信用户接口")
@RestController
@RequestMapping("/weixinusers")
public class WeixinUserController {
    @Autowired
    private WeixinUserService service;

    @ApiOperation(value = "搜索微信用户")
    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<WeixinUserDto>> searchWeixinUsers(
            @ApiParam(value = "查询微信用户昵称") @RequestParam(required = false) String nickName,
            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
        return success(service.searchWeixinUsers(nickName, currentPage, pageSize));
    }

    @ApiOperation(value = "添加微信用户")
    @PostMapping
    public GenericResponse addWeixinUser(
            @ApiParam(value = "微信用户信息", required = true) @Validated @RequestBody WeixinUserParam weixinUserParam) {
        service.addWeixinUser(weixinUserParam);

        return success();
    }

    @ApiOperation(value = "更新微信用户")
    @PutMapping("/{id}")
    public GenericResponse editWeixinUser(
            @ApiParam(value = "微信用户Id", required = true) @PathVariable Long id,
            @ApiParam(value = "微信用户信息", required = true) @Validated @RequestBody WeixinUserParam weixinUserParam) {
        service.editWeixinUser(id, weixinUserParam);

        return success();
    }

    @ApiOperation(value = "删除微信用户")
    @DeleteMapping("/{id}")
    public GenericResponse removeWeixinUser(
            @ApiParam(value = "微信用户Id", required = true) @PathVariable long id) {
        service.removeWeixinUser(id);

        return success();
    }
}
