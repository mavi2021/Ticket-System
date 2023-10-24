package com.example.pay.enums;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @create 2023/10/24 20:39
 */
@RequiredArgsConstructor
public enum TradeStatusEnum {

    /**
     * 交易创建，等待买家付款
     */
    WAIT_BUYER_PAY(0, ListUtil.of("WAIT_BUYER_PAY")),

    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED(10, ListUtil.of("TRADE_CLOSED")),

    /**
     * 交易支付成功
     */
    TRADE_SUCCESS(20, ListUtil.of("TRADE_SUCCESS")),

    /**
     * 交易结束，不可退款
     */
    TRADE_FINISHED(30, ListUtil.of("TRADE_FINISHED"));

    @Getter
    private final Integer tradeCode;

    @Getter
    private final List<String> tradeStatus;

}
