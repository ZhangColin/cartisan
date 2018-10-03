package com.cartisan.management.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: SystemController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Controller
public class SystemController {
    @RequestMapping(value = {"/", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

}
