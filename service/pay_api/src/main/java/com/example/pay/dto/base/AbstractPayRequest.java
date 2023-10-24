package com.example.pay.dto.base;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

public abstract class AbstractPayRequest implements PayRequest {

    /**
     * 交易环境，H5、小程序、网站等
     */
    @Getter
    @Setter
    private Integer tradeType;

    /**
     * 订单号
     */
    @Getter
    @Setter
    private String orderSn;

    /**
     * 支付渠道
     */
    @Getter
    @Setter
    private Integer channel;


    /**
     * 订单总金额
     * 单位为元，精确到小数点后两位，取值范围：[0.01,100000000]
     */
    @Getter
    @Setter
    private BigDecimal totalAmount;

    /**
     * 商户订单号
     * 由商家自定义，64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复
     */
    @Getter
    @Setter
//    private String orderRequestId = SnowflakeIdUtil.nextIdStr();
    private String orderRequestId = String.valueOf(UUID.randomUUID());


}
