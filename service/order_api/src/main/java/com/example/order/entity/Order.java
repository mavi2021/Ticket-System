package com.example.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @create 2023/7/11 10:27
 */
@Data
@Builder
@TableName("t_order_0")
public class Order extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 列车id
     */
    private Long trainId;

    /**
     * 列车车次
     */
    private String trainNumber;

    /**
     * 出发站点
     */
    private String departure;

    /**
     * 到达站点
     */
    private String arrival;

    /**
     * 订单来源
     */
    private Integer source;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 乘车日期
     */
    private Date ridingDate;

    /**
     * 出发时间
     */
    private Date departureTime;

    /**
     * 出发时间
     */
    private Date arrivalTime;
}
