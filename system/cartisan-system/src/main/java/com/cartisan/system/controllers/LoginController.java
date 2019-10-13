package com.cartisan.system.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.system.dtos.UserDto;
import com.cartisan.system.params.LoginParam;
import com.cartisan.system.queries.PermissionQueryMapper;
import com.cartisan.system.services.LoginService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private PermissionQueryMapper permissionQueryMapper;

    @PostMapping("login")
    public GenericResponse login(
            @ApiParam(value = "登录信息", required = true) @Validated @RequestBody LoginParam loginParam) {
        final String token = loginService.login(loginParam);
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);

        return success(data);
    }

    @PostMapping("logout")
    public GenericResponse logout(@RequestHeader(name = "X-Token") String token) {
        loginService.logout(token);
        return success();
    }

    @GetMapping("user/info")
    public GenericResponse info(@RequestHeader(name = "X-Token") String token) {
        final UserDto user = loginService.getUserByToken(token);
        final List<String> roles = user.getRoleCodes();
        final List<String> permissions = permissionQueryMapper.getPermissionCodesByRoleCodes(roles);

        Map<String, Object> info = new HashMap<>();
        info.put("roles", roles);
        info.put("permissions", permissions);
        info.put("name", user.getUsername());
        info.put("avatar", user.getAvatar());

        return success(info);
    }
}
