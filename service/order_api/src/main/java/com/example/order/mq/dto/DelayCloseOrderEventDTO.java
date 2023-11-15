package com.example.order.mq.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @date 2023/11/15 22:52
 */
@Data
@Builder
public class DelayCloseOrderEventDTO {

    /**
     * 车次 ID
     */
    private String trainId;

    /**
     * 出发站点
     */
    private String departure;

    /**
     * 到达站点
     */
    private String arrival;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 乘车人购票信息
     */
    private List<com.example.ticket.dto.req.TrainPurchaseTicketRespDTO> trainPurchaseTicketResults;
}
