package com.example.pay.service;

import com.example.pay.dto.req.AliPayRequest;
import com.example.pay.dto.req.AliRefundRequest;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.entity.Pay;
import com.example.pay.enums.PayChannelEnum;
import com.example.pay.enums.TradeStatusEnum;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @create 2023/10/24 20:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayServiceTest {

    @Reference(version = "1.0.0")
//    @Autowired
    private PayService payService;

    @Autowired
    private PayService payService2;

    @Test
    public void test(){
        System.out.println("11");
    }

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

    @Test
    public void testDatabase(){
        List<Pay> list = payService.list();
        System.out.println(list);
    }

}
