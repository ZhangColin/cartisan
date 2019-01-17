package com.cartisan.management.modules.base.controllers;

import com.cartisan.common.entity.Result;
import com.cartisan.common.entity.StatusCode;
import com.cartisan.management.modules.base.dtos.ContinentDto;
import com.cartisan.management.modules.base.gateways.ContinentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: ContinentController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    private ContinentClient client;

    @GetMapping
    public Result<List<ContinentDto>> findContinents() {
        return new Result<>(true, StatusCode.OK, "查询成功", client.findContinents());
    }
}
