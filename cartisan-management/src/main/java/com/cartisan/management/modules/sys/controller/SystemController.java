package com.cartisan.management.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title: SystemController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Controller
public class SystemController {
    @GetMapping("/{module}/{page}.html")
    public String module(@PathVariable("module") String module,
                         @PathVariable("page") String page) {
        return "modules/" + module + "/" + page;
    }

    @GetMapping(value = {"/", "index.html"})
    public String index() {
        return "index";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

}
