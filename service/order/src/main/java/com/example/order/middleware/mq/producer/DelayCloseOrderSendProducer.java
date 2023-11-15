package com.example.order.middleware.mq.producer;

import com.example.order.mq.dto.BaseSendExtendDTO;
import com.example.order.mq.dto.DelayCloseOrderEventDTO;
import com.example.order.mq.producer.AbstractCommonSendProduceProducer;
import com.mysql.cj.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DelayCloseOrderSendProducer implements AbstractCommonSendProduceProducer {

        @Resource
        private RocketMQTemplate rocketMQTemplate;

        public void sendMessage(DelayCloseOrderEventDTO messageSendEvent){
            BaseSendExtendDTO baseSendExtendDTO = buildBaseSendExtendParam(messageSendEvent);
            Message<DelayCloseOrderEvent> message = MessageBuilder.withPayload(messageSendEvent).build();
            rocketMQTemplate.syncSend(baseSendExtendDTO.getTopic()+":"+baseSendExtendDTO.getTag(),
                    message,
                    baseSendExtendDTO.getSentTimeout(),
                    baseSendExtendDTO.getDelayLevel());
        }

        @Override
        public BaseSendExtendDTO buildBaseSendExtendParam(DelayCloseOrderEventDTO messageSendEvent) {
            return BaseSendExtendDTO.builder()
                    .eventName("延迟关闭订单")
                    .topic(OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY)
                    .tag(OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY)
                    .keys(messageSendEvent.getOrderSn())
                    .sentTimeout(2000L)
                    .delayLevel(14).build();
        }
    }

