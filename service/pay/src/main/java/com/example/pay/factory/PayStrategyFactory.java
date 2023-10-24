package com.example.pay.factory;

import com.example.pay.enums.PayChannelEnum;
import com.example.pay.strategy.PayStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @create 2023/10/23 20:24
 */
@Component
public class PayStrategyFactory implements AbstractPayStrategyFactory {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public PayStrategy getPayStrategy(Integer payType) {
        return (PayStrategy) applicationContext.getBean(PayChannelEnum.getBeanNameByPayType(payType));
    }
}
