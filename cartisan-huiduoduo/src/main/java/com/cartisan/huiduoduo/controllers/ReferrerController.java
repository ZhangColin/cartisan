package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.ReferrerDto;
import com.cartisan.huiduoduo.params.ReferrerParam;
import com.cartisan.huiduoduo.services.ReferrerService;
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
@Api(tags = "推荐人接口")
@RestController
@RequestMapping("/referrers")
public class ReferrerController {
    @Autowired
    private ReferrerService service;

    @ApiOperation(value = "搜索推荐人")
    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<ReferrerDto>> searchReferrers(
            @ApiParam(value = "查询商户名") @RequestParam(required = false) String nickName,
            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
        return success(service.searchReferrers(nickName, currentPage, pageSize));
    }

    @ApiOperation(value = "添加推荐人")
    @PostMapping
    public GenericResponse addReferrer(
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody ReferrerParam referrerParam) {
        service.addReferrer(referrerParam);

        return success();
    }

    @ApiOperation(value = "更新推荐人")
    @PutMapping("/{id}")
    public GenericResponse editReferrer(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable Long id,
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody ReferrerParam referrerParam) {
        service.editReferrer(id, referrerParam);

        return success();
    }

    @ApiOperation(value = "删除推荐人")
    @DeleteMapping("/{id}")
    public GenericResponse removeReferrer(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable long id) {
        service.removeReferrer(id);

        return success();
    }
}
