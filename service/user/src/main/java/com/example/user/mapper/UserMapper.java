package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/7/10 10:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
