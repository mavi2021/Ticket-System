package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.Result;
import com.example.user.entity.Passenger;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/9/20 21:16
 */
@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {
}
