package com.example.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.order.entity.OrderItem;

import java.util.List;

/**
 * @create 2023/10/20 14:45
 */
public interface OrderItemService extends IService<OrderItem> {

    List<OrderItem> selectOrderItemList(String orderSn);
}
