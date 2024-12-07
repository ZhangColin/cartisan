package com.cartisan.controller;

import com.cartisan.entity.User;
import com.cartisan.entity.UserInfo;
import com.cartisan.mapper.UserInfoMapper;
import com.cartisan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user); // 创建用户
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null); // 根据ID获取用户
    }

    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userInfoMapper.selectList(null);
    }
}