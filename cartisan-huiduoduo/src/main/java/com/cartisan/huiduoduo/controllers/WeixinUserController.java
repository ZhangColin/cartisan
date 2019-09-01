package com.cartisan.huiduoduo.controllers;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.huiduoduo.dtos.WeiXinUserDto;
import com.cartisan.huiduoduo.params.BindReferrerParam;
import com.cartisan.huiduoduo.params.WeiXinEncryptedDataParam;
import com.cartisan.huiduoduo.services.WeixinUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@Api(tags = "微信用户接口")
@RestController
@RequestMapping("/weixinusers")
@Slf4j
public class WeixinUserController {
    @Autowired
    private WeixinUserService service;

    @ApiOperation(value = "搜索微信用户")
    @GetMapping("/search/{currentPage}/{pageSize}")
    public GenericResponse<PageResult<WeiXinUserDto>> searchWeixinUsers(
            @ApiParam(value = "查询微信用户昵称") @RequestParam(required = false) String nickName,
            @ApiParam(value = "页码", required = true) @PathVariable Integer currentPage,
            @ApiParam(value = "每页记录数", required = true) @PathVariable Integer pageSize) {
        return success(service.searchWeiXinUsers(nickName, currentPage, pageSize));
    }


    @ApiOperation(value = "微信用户登录")
    @GetMapping("/login")
    public GenericResponse<JSONObject> login(
            @ApiParam(value = "腾讯提供的SessionCode", required = true) @Validated String sessionCode) {
        log.info("SessionCode：" + sessionCode);
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        final String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxaa496b8f36ca4d99&secret=30ec25053da7263619e521afb5a1f1f4&js_code="
                + sessionCode + "&grant_type=authorization_code";
        final JSONObject loginResult = restTemplate.getForObject(
                url, JSONObject.class);

        log.info("WeiXin login return: " + loginResult.toJSONString());
        service.login(loginResult.getString("openid"));

        return success(loginResult);
    }


    @ApiOperation(value = "填充用户信息")
    @PutMapping("/fillUserInfo")
    public GenericResponse<WeiXinUserDto> fillUserInfo(
            @ApiParam(value = "微信用户信息加密数据", required = true) @Validated @RequestBody WeiXinEncryptedDataParam weiXinEncryptedDataParam) {
        final String userDataJsonString = WXCore.decrypt("wxaa496b8f36ca4d99", weiXinEncryptedDataParam.getEncryptedData(),
                weiXinEncryptedDataParam.getSessionKey(), weiXinEncryptedDataParam.getIv());
        log.info(userDataJsonString);
        final JSONObject weiXinUserJson = JSONObject.parseObject(userDataJsonString);

        return success(service.fillWeiXinUserInfo(
                weiXinUserJson.getString("openId"),
                weiXinUserJson.getString("nickName"),
                weiXinUserJson.getInteger("gender"),
                weiXinUserJson.getString("language"),
                weiXinUserJson.getString("country"),
                weiXinUserJson.getString("province"),
                weiXinUserJson.getString("city"),
                weiXinUserJson.getString("avatarUrl")));
    }


    @ApiOperation(value = "绑定推荐人")
    @PutMapping("/{userId}/bindReferrer")
    public GenericResponse<WeiXinUserDto> bindReferrer(
            @ApiParam(value = "用户Id", required = true) @PathVariable Long userId,
            @ApiParam(value = "推荐人", required = true) @RequestBody BindReferrerParam bindReferrerParam) {
        log.info(bindReferrerParam.getReferrerId());
        return success(service.bindReferrer(userId, Long.parseLong(bindReferrerParam.getReferrerId())));
    }


}
