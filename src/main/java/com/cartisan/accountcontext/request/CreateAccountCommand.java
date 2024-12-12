package com.cartisan.accountcontext.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

/**
 * @author zhangcolin
 */
public record CreateAccountCommand(
        @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED) String username,
        @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED) String nickname,
        @Schema(description = "手机") String phone,
        @Schema(description = "邮箱") @Email String email) {
}

