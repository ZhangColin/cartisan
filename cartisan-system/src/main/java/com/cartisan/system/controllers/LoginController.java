package com.cartisan.system.controllers;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.common.utils.JwtUtil;
import com.cartisan.system.params.LoginParam;
import com.cartisan.system.queries.PermissionQuery;
import com.cartisan.system.services.LoginService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.cartisan.common.responses.GenericResponse.success;

/**
 * @author colin
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private PermissionQuery permissionQuery;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public GenericResponse login(
            @ApiParam(value = "登录信息", required = true) @Validated @RequestBody LoginParam loginParam) {
        final String token = loginService.login(loginParam);
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);

        return success(data);
    }

    @PostMapping("logout")
    public GenericResponse logout() {
        return success();
    }

    @GetMapping("user/info")
    public GenericResponse info(@ApiParam(value = "用户登录token", required = true) @RequestParam String token) {
        final Claims claims = jwtUtil.parseJWt(token);
        HashMap<String, Object> info = new HashMap<>(3);
        final List<String> roles = (List<String>)claims.get("roles");
        final List<String> permissions = permissionQuery.getPermissionCodesByRoleCodes(roles);

        info.put("roles", roles);
        info.put("permissions", permissions);
        info.put("name", claims.get("name"));
        info.put("avatar", claims.get("avatar"));

        return success(info);
    }
}
