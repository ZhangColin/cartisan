package com.cartisan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class UserInfo {
    private Long id;
    private String name;
    private String email;
}
