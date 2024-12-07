package com.cartisan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cartisan.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangcolin
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
