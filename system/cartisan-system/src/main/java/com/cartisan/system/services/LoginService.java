package com.cartisan.system.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.RedisUtil;
import com.cartisan.system.constants.LoginKey;
import com.cartisan.system.constants.SystemCodeMessage;
import com.cartisan.system.domains.User;
import com.cartisan.system.dtos.UserDto;
import com.cartisan.system.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author colin
 */
@Service
public class LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    public String login(LoginParam loginParam) {
        final Optional<User> userOptional = userService.findByUserName(loginParam.getUsername());

        final User user = userOptional
                .orElseThrow(() -> new CartisanException(SystemCodeMessage.ERROR_USERNAME_OR_PASSWORD));

        if (!user.valid(loginParam.getPassword())) {
            throw new CartisanException(SystemCodeMessage.ERROR_USERNAME_OR_PASSWORD);
        }


        final String token = UUID.randomUUID().toString().replace("-", "");

        redisUtil.set(LoginKey.token, token, UserDto.convertFrom(user));

        return token;

    }

    public void logout(String token) {
        // 清除 token 缓存
        redisUtil.delete(LoginKey.token, token);

        // 清除角色缓存
    }

    public UserDto getUserByToken(String token) {
        return redisUtil.get(LoginKey.token, token);
    }
}
