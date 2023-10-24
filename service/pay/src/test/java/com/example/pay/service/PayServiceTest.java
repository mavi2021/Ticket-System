package com.example.pay.service;

import com.example.pay.dto.req.AliPayRequest;
import com.example.pay.dto.req.AliRefundRequest;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.enums.PayChannelEnum;
import com.example.pay.enums.TradeStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

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

    @Test
    public void getPayInfoByOrderSn(){
        PayInfoRespDTO payInfoByOrderSn = payService.getPayInfoByOrderSn(String.valueOf(1123123));
        System.out.println(payInfoByOrderSn);
    }

    @Test
    public void getPayInfoByPaySn(){
        PayInfoRespDTO payInfoByPaySn = payService.getPayInfoByPaySn("311cfedc-d83d-4d05-a578-502a7dc6bbc9");
        System.out.println(payInfoByPaySn);
    }

    @Test
    public void commonRefund(){
        AliRefundRequest refundRequest = AliRefundRequest.builder().build();
        refundRequest.setChannel(0);
        refundRequest.setOrderSn("1123123");
        payService.commonRefund(refundRequest);
    }

    @Test
    public void callbackPay(){
        PayCallbackReqDTO payCallbackReqDTO = PayCallbackReqDTO.builder()
                .payAmount(100)
                .orderSn("1123123")
                .gmtPayment(new Date())
                .tradeNo("asdkolsak")
                .status(TradeStatusEnum.TRADE_SUCCESS.getTradeCode())
                .build();
        payService.callbackPay(payCallbackReqDTO);
    }

}
