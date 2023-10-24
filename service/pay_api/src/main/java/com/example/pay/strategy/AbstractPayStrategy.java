package com.example.pay.strategy;

import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.dto.base.pay.PayResponse;
import com.example.pay.dto.base.refund.RefundRequest;
import com.example.pay.dto.base.refund.RefundResponse;

/**
 * @create 2023/10/23 20:30
 */
public abstract class AbstractPayStrategy implements PayStrategy{
    @Override
    public PayResponse pay(PayRequest payRequest) {
        return null;
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        return null;
    }

}
