package com.example.ticket.middleware.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketMqTest {



    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void connectionTest(){
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.convertAndSend("Demo:base", "test"+i);

        }
    }

}
