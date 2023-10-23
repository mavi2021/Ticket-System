package com.example.pay.factory;

import com.example.pay.strategy.PayStrategy;

/**
 * @create 2023/10/23 20:26
 */
public interface AbstractPayStrategyFactory {
    PayStrategy getPayStrategy(Integer payType);

}
