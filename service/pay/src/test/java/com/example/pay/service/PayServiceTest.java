package com.example.pay.service;

import com.example.pay.dto.req.AliPayRequest;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.enums.PayChannelEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @create 2023/10/24 20:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayServiceTest {

    @Autowired
    private PayService payService;
    @Test
    public void commonPay(){
        AliPayRequest payRequest = AliPayRequest.builder()
                .outOrderSn("111")
                .tradeNo("aa")
                .subject("衣物")
                .build();
        payRequest.setChannel(PayChannelEnum.ALI_PAY.getPayChannel());
        payRequest.setTotalAmount(new BigDecimal("100.564"));
        PayRespDTO payRespDTO = payService.commonPay(payRequest);
        System.out.println(payRespDTO);
    }

}
