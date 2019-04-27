package com.cartisan.system.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.JwtUtil;
import com.cartisan.common.utils.RedisUtil;
import com.cartisan.system.domains.User;
import com.cartisan.system.domains.UserRole;
import com.cartisan.system.params.LoginParam;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginParam loginParam) {
        final Optional<User> userOptional = userService.findByUserName(loginParam.getUsername());

        if (!userOptional.isPresent()) {
            throw new CartisanException("用户名或密码不正确");
        }

        final User user = userOptional.get();

        if (!user.valid(loginParam.getPassword())) {
            throw new CartisanException("用户名或密码不正确");
        }


        final String token = jwtUtil.createJwt(user.getId().toString(),
                ImmutableMap.<String, Object>builder()
                        .put("name", user.getUsername())
                        .put("avatar", user.getAvatar())
                        .put("roles", user.getRoles().stream().map(UserRole::getRoleCode).collect(toList()))
                        .build());

        redisUtil.getValueOperator().set("PREFIX_USER_TOKEN_" + token, token, 1800, TimeUnit.SECONDS);

        return token;

    }
}
