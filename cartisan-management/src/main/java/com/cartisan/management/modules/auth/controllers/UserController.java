package com.cartisan.management.modules.auth.controllers;

import com.cartisan.common.responses.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.cartisan.common.responses.GenericResponse.success;
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

        return success(data);
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
        info.put("avatar", "");

        return success(info);
    }
}
