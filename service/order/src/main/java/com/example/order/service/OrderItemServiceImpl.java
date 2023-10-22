package com.example.order.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.mapper.OrderItemMapper;
import com.example.order.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2023/10/20 14:46
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Override
    public List<OrderItem> selectOrderItemList(String orderSn) {
        LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = Wrappers.lambdaQuery(OrderItem.class)
                .eq(OrderItem::getOrderSn, orderSn);
        return baseMapper.selectList(orderItemLambdaQueryWrapper);
    }
}
