package com.example.pay.strategy;

import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.dto.base.pay.PayResponse;
import com.example.pay.dto.base.refund.RefundRequest;
import com.example.pay.dto.base.refund.RefundResponse;
import com.example.pay.enums.TradeStatusEnum;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/23 20:43
 */
@Component
public final class AliPayStrategy extends AbstractPayStrategy{
    @Override
    public PayResponse pay(PayRequest payRequest) {
        System.out.println("支付宝");
        return null;
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        System.out.println("支付宝退款");
        return RefundResponse.builder().status(TradeStatusEnum.TRADE_CLOSED.getTradeCode()).build();
    }
}
