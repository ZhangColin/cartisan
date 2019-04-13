package com.cartisan.management.modules.auth.controller;

import com.cartisan.common.response.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.cartisan.common.response.GenericResponse.success;
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
    public GenericResponse login() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", "colin");

        return success();
    }

    @PostMapping("logout")
    public GenericResponse logout() {
        return success();
    }

    @GetMapping("info")
    public GenericResponse info() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("roles", asList("admin"));
        info.put("role", asList("admin"));
        info.put("name", "colin");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c‐e4f8‐4870‐b634‐56703 b4acafe.gif");

        return success(info);
    }
}
