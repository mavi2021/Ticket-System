package com.example.ticket.common.enums;

import lombok.Getter;
import lombok.Setter;

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
     * 检票中
     */
    CHECKING(2),

    /**
     * 已进站
     */
    BOARDED(3),

    /**
     * 已改签
     */
    CHANGED(4),

    /**
     * 已退票
     */
    REFUNDED(5),

    /**
     * 已取消
     */
    CLOSED(6);

    @Getter
    private final Integer code;

    TicketStatusEnum(Integer code) {
        this.code = code;
    }
}
