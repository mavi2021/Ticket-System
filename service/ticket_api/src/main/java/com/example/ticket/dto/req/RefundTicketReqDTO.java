package com.example.ticket.dto.req;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RefundTicketReqDTO{

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 退款类型 0 部分退款 1 全部退款
     */
    private Integer type;

    /**
     * 部分退款子订单记录id集合
     */
    private List<Long> subOrderRecordIdReqList;
}
