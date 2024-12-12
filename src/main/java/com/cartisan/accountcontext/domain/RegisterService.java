package com.cartisan.accountcontext.domain;

import com.cartisan.infrastructure.util.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangcolin
 */
@Service
@Slf4j
@AllArgsConstructor
public class RegisterService {
    private final SnowflakeIdWorker snowflakeIdWorker;

    public User register(String username, String nickname) {
        User user = new User(snowflakeIdWorker.nextId(), username, nickname, "");

        return user;
    }
}
