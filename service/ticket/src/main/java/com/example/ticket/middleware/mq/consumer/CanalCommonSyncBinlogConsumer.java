package com.example.ticket.middleware.mq.consumer;

import com.example.ticket.common.constant.RocketMQConstant;
import com.example.ticket.dto.event.CanalBinlogEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = RocketMQConstant.CANAL_COMMON_SYNC_CG_KEY, topic = RocketMQConstant.CANAL_COMMON_SYNC_TOPIC_KEY)
public class CanalCommonSyncBinlogConsumer implements RocketMQListener<CanalBinlogEvent> {

    @Override
    public void onMessage(CanalBinlogEvent message) {
        System.out.println(message);
    }
}
