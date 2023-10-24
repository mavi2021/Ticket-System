package com.example.pay.executor;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;

/**
 * @create 2023/10/23 22:15
 */
public interface AbstractPayStrategyExecutor {

    PayResponse pay(PayRequest payRequest);

}
