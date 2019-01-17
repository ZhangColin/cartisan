package com.cartisan.management.modules.auth.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static java.util.Arrays.asList;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("login")
    public Result login() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", "colin");

        return new Result(true, StatusCode.OK, "登录成功",data);
    }

    @PostMapping("logout")
    public Result logout() {
        return new Result(true, StatusCode.OK, "退出成功");
    }

    @GetMapping("info")
    public Result info() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("roles", asList("admin"));
        info.put("role", asList("admin"));
        info.put("name", "colin");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c‐e4f8‐4870‐b634‐56703 b4acafe.gif");

        return new Result(true, StatusCode.OK, "获取信息成功", info);
    }
}
