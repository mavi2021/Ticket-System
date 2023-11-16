package com.example.order.middleware.mq.consumer;

import com.example.order.common.constant.OrderRocketMQConstant;
import com.example.order.mq.dto.DelayCloseOrderEventDTO;
import com.example.order.service.OrderService;
import com.example.ticket.dto.req.CancelTicketOrderReqDTO;
import com.example.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @date 2023/11/16 21:46
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = OrderRocketMQConstant.ORDER_DELAY_CLOSE_CG_KEY, topic = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY)
public class DelayCloseOrderConsumer implements RocketMQListener<DelayCloseOrderEventDTO> {

    @Reference(version = "1.0.O", check = false)
    private TicketService ticketService;

    @Override
    public void onMessage(DelayCloseOrderEventDTO message) {
        CancelTicketOrderReqDTO cancelTicketOrderReqDTO = CancelTicketOrderReqDTO.builder()
                .build();
        ticketService.cancelTicketOrder(cancelTicketOrderReqDTO);
    }
}
