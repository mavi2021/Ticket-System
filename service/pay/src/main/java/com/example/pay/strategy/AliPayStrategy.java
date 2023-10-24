package com.example.pay.strategy;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;
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
}
