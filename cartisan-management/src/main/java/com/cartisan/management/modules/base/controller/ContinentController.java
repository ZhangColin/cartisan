package com.cartisan.management.modules.base.controller;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.management.modules.base.dto.ContinentDto;
import com.cartisan.management.modules.base.gateway.ContinentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.common.response.GenericResponse.success;


/**
 * @author colin
 */
@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    private ContinentClient client;

    @GetMapping
    public GenericResponse<List<ContinentDto>> findContinents() {
        return success(client.findContinents());
    }
}
