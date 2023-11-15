package com.example.order.mq.producer;

import com.example.order.mq.dto.BaseSendExtendDTO;
import com.example.order.mq.dto.DelayCloseOrderEventDTO;

public interface AbstractCommonSendProduceProducer { void sendMessage(DelayCloseOrderEventDTO messageSendEvent);

    BaseSendExtendDTO buildBaseSendExtendParam(DelayCloseOrderEventDTO messageSendEventDTO);

}
