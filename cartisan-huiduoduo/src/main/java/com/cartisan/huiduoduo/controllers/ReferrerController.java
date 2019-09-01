package com.cartisan.huiduoduo.controllers;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.ReferrerDto;
import com.cartisan.huiduoduo.dtos.ReferrerInfo;
import com.cartisan.huiduoduo.params.GeneralizeApplyParam;
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

    @ApiOperation(value = "根据用户获取推荐人信息")
    @GetMapping("/getByUserId/{userId}")
    public GenericResponse<ReferrerInfo> getReferrer(
            @ApiParam(value = "用户Id", required = true) @PathVariable Long userId) {

        return success(service.getReferrerInfoByUserId(userId));
    }

    @ApiOperation(value = "申请成为推荐人")
    @PostMapping
    public GenericResponse generalizeApply(
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody GeneralizeApplyParam referrerParam) {
        service.generalizeApply(referrerParam);

        return success();
    }

    @ApiOperation(value = "推荐人审核通过")
    @PutMapping("/{id}/audit")
    public GenericResponse generalizeAudit(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable Long id) {
        service.generalizeAudit(id);

        return success();
    }

    @ApiOperation(value = "更新推荐人")
    @PutMapping("/{id}")
    public GenericResponse editReferrer(
            @ApiParam(value = "推荐人Id", required = true) @PathVariable Long id,
            @ApiParam(value = "推荐人信息", required = true) @Validated @RequestBody GeneralizeApplyParam referrerParam) {
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
