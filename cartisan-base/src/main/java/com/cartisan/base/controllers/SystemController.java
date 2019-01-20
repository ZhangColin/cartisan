package com.cartisan.base.controllers;

import com.cartisan.base.config.CartisanConfig;
import com.cartisan.base.dtos.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author colin
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private CartisanConfig cartisanConfig;

    @GetMapping("/info")
    public SystemInfo getSystemInfo() {
        return new SystemInfo(cartisanConfig.getName(), cartisanConfig.getVersion());
    }
}

