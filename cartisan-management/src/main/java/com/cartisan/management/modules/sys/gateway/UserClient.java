package com.cartisan.management.modules.sys.gateway;

import com.cartisan.management.modules.sys.model.User;
import com.cartisan.management.modules.sys.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: UserClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
@Slf4j
public class UserClient {
    public User getUser(String username) {
        final User user = new User();
        user.setUsername("colin");
        user.setPassword(ShiroUtils.sha256("123456", "salt"));
        user.setSalt("salt");

        user.setStatus(1);
        return user;
    }

    public List<String> getAllPerms(Long userId) {
        return new ArrayList<>();
    }

    public List<Long> getAllMenus(Long userId) {
        return new ArrayList<>();
    }
}
