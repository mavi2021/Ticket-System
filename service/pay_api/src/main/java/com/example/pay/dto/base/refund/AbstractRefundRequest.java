package com.example.pay.dto.base.refund;

import lombok.Data;

/**
 * @create 2023/10/24 21:28
 */
@Data
public abstract class AbstractRefundRequest implements RefundRequest{

    /**
     * 交易环境，H5、小程序、网站等
     */
    private Integer tradeType;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 支付渠道
     */
    private Integer channel;

}
