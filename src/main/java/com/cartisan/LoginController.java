package com.cartisan;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cartisan.infrastructure.response.GenericResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangcolin
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public GenericResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        final GenericResponse success = GenericResponse.success();
        return success;

//        if ("admin".equals(loginRequest.username()) && "admin".equals(loginRequest.password())) {
//            StpUtil.login(10001);
//
//            final SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//            return SaResult.data(tokenInfo);
//        }
//
//        return SaResult.error("登录失败。");
    }

    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();

        return SaResult.ok("退出登录成功。");
    }
}
