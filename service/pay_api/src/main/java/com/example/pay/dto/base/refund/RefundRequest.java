package com.example.pay.dto.base.refund;

/**
 * @create 2023/10/24 21:27
 */
public interface RefundRequest {

    /**
     * 获取订单号
     */
    String getOrderSn();

    /**
     * 获取支付渠道
     */
    Integer getChannel();
}
