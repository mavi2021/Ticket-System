package com.example.ticket.middleware.mq.producer;

import com.example.ticket.dto.mq.event.DelayCloseOrderEvent;
import org.springframework.stereotype.Component;

/**
 * @date 2023/11/15 22:47
 */
@Component
public class DelayCloseOrderSendProducer {

    public void sendMessage(DelayCloseOrderEvent messageSendEvent){

    }
}
