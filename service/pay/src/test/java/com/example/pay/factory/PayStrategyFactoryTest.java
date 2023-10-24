package com.example.pay.factory;

import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.strategy.PayStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PayStrategyFactoryTest
{
    @Resource
    private PayStrategyFactory payStrategyFactory;

    @Test
    public void getPayStrategy(){
        PayStrategy payStrategy = payStrategyFactory.getPayStrategy(0);
        PayRequest pp = null;
        payStrategy.pay(pp);
    }
}
