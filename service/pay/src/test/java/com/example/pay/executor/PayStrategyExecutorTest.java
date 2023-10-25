package com.example.pay.executor;

import com.example.pay.dto.req.AliPayRequest;
import com.example.pay.dto.req.AliRefundRequest;
import com.example.pay.enums.PayChannelEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @create 2023/10/24 19:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayStrategyExecutorTest {

    @Autowired
    private PayStrategyExecutor payStrategyExecutor;

    @Test
    public void pay(){
        AliPayRequest payRequest = AliPayRequest.builder()
                .outOrderSn("111")
                .tradeNo("aa")
                .subject("衣物")
                .build();
        payRequest.setChannel(PayChannelEnum.ALI_PAY.getPayChannel());
        payStrategyExecutor.pay(payRequest);
    }

    @Test
    public void refund(){
        AliRefundRequest refundRequest = AliRefundRequest.builder().build();
        refundRequest.setChannel(1);
        payStrategyExecutor.refund(refundRequest);
    }

}
