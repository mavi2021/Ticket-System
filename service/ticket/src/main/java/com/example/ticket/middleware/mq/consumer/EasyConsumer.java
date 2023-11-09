package com.example.ticket.middleware.mq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

//@Component
//@RocketMQMessageListener(consumerGroup = "demo", topic = "Demo")
class EasyConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("consume message:"+message);
    }
}
