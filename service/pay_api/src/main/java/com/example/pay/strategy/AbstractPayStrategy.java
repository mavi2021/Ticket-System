package com.example.pay.strategy;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;

/**
 * @create 2023/10/23 20:30
 */
public abstract class AbstractPayStrategy implements PayStrategy{
    @Override
    public PayResponse pay(PayRequest payRequest) {
        return null;
    }
}
