package com.example.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/7/11 10:38
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
