package com.cartisan.accountcontext.response;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cartisan.infrastructure.dp.OnOffStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author colin
 */
@TableName("acc_users")
public record UserResponse(
        @Schema(description = "用户Id") String id,
        @Schema(description = "用户名") String username,
        @Schema(description = "电话") String phone,
        @Schema(description = "邮箱") String email,
        @Schema(description = "昵称") String nickname,
        @Schema(description = "头像") String avatar,
        @Schema(description = "签名") String motto,
        @Schema(description = "状态") OnOffStatus status) {
}
