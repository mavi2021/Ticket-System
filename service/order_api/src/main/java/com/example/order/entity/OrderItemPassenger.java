package com.example.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/21 20:01
 */
@Data
@Builder
@TableName("t_order_item_passenger_0")
public class OrderItemPassenger extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;
}
