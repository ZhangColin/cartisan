package com.cartisan.management.modules.sys.controller;

import com.cartisan.management.modules.sys.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @GetMapping("/list")
    public HashMap<String, Object> list(String draw) {
        final ArrayList<User> users = new ArrayList<>();

        final HashMap<String, Object> result = new HashMap<>();

        final User user = new User(1L, "colin");


        users.addAll(asList(user));

        result.put("draw", draw);
        result.put("recordsTotal", 1);
        result.put("recordsFiltered", 1);
        result.put("data", users);

        return result;

    }
}
