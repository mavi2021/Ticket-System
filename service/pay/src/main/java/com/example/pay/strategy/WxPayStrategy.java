package com.example.pay.strategy;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/23 21:47
 */
@Component
public class WxPayStrategy extends AbstractPayStrategy{
    @Override
    public PayResponse pay(PayRequest payRequest) {
        System.out.println("微信支付");
        return null;
    }
}
