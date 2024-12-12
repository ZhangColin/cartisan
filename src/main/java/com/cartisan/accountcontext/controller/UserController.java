package com.cartisan.accountcontext.controller;

import com.cartisan.accountcontext.application.UserAppService;
import com.cartisan.accountcontext.request.CreateAccountCommand;
import com.cartisan.infrastructure.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.infrastructure.response.GenericResponse.success;

/**
 * @author zhangcolin
 */
@Tag(name = "系统管理 - 用户")
@RestController
@RequestMapping("/system/users")
@Validated
@Slf4j
@AllArgsConstructor
public class UserController {
    private final UserAppService userAppService;

    @Operation(summary = "创建用户账号")
    @PostMapping
    public GenericResponse createAccount(
            @Parameter(description = "账号信息", required = true) @RequestBody @Valid CreateAccountCommand createAccountCommand) {
        return success(userAppService.createAccount(createAccountCommand));
    }

    @Operation(summary = "获取用户")
    @GetMapping("/{id}")
    public GenericResponse getUser(
            @Parameter(description = "用户Id", required = true) @PathVariable Long id) {
        return success(userAppService.getUserById(id));
    }
}
