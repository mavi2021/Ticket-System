package com.example.ticket.middleware.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;

import javax.annotation.Resource;

public class RocketMqTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void connectionTest(){
        rocketMQTemplate.convertAndSend("Demo:base", "test");
    }
}
