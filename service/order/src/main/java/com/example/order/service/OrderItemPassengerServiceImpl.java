package com.example.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.entity.OrderItemPassenger;
import com.example.order.mapper.OrderItemPassengerMapper;
import org.springframework.stereotype.Service;

/**
 * @create 2023/10/21 20:04
 */
@Service
public class OrderItemPassengerServiceImpl extends ServiceImpl<OrderItemPassengerMapper, OrderItemPassenger> implements OrderItemPassengerService{
}
