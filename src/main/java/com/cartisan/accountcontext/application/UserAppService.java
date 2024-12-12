package com.cartisan.accountcontext.application;

import com.cartisan.accountcontext.domain.RegisterService;
import com.cartisan.accountcontext.domain.User;
import com.cartisan.accountcontext.mapper.UserMapper;
import com.cartisan.accountcontext.repository.UserRepository;
import com.cartisan.accountcontext.request.CreateAccountCommand;
import com.cartisan.accountcontext.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangcolin
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserAppService {
    private final RegisterService registerService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long createAccount(CreateAccountCommand createAccountCommand) {
        final User newUser = registerService.register(createAccountCommand.username(), createAccountCommand.nickname());

        newUser.changePhone(createAccountCommand.phone());
        newUser.changeEmail(createAccountCommand.email());

        userRepository.save(newUser);
        return newUser.getId();
    }

    public UserResponse getUserById(Long id) {
        return this.userMapper.selectById(id);
    }
}
