package com.example.ticket.service.remote;

import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.service.PayService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @create 2023/10/25 15:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RemotePayServiceTest {

    @Reference(version = "1.0.0")
    private PayService payService;

    @Test
    public void getPayInfo(){
        PayInfoRespDTO payInfoByOrderSn = payService.getPayInfoByOrderSn("1123123");
        System.out.println(payInfoByOrderSn);
    }
}
