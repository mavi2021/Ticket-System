package com.example.pay.executor;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;
import com.example.pay.factory.PayStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/23 22:13
 */
@Component
public class PayStrategyExecutor implements AbstractPayStrategyExecutor{

    @Autowired
    private PayStrategyFactory payStrategyFactory;

    @Override
    public PayResponse pay(PayRequest payRequest) {
//        payStrategyFactory.getPayStrategy(payRequest.getT)

        return null;
    }
}
