package com.example.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @create 2023/10/20 14:44
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
