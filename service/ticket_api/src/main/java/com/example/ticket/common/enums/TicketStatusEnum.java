package com.example.ticket.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @create 2023/10/27 19:52
 */
public enum TicketStatusEnum {

    /**
     * 未支付
     */
    UNPAID(0),

    /**
     * 已支付
     */
    PAID(1),

    /**
     * 已进站
     */
    BOARDED(2),

    /**
     * 已改签
     */
    CHANGED(3),

    /**
     * 已退票
     */
    REFUNDED(4),

    /**
     * 已取消
     */
    CLOSED(5);

    @Getter
    private final Integer code;

    TicketStatusEnum(Integer code) {
        this.code = code;
    }
}
