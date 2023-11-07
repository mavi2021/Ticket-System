package com.example.ticket.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @date 2023/11/8 19:22
 */
@Data
@Builder
public class SeatTypeCountRespDTO {

    /**
     * 座位类型
     */
    private Integer seatType;

    /**
     * 座位类型 - 对应数量
     */
    private Integer seatCount;
}
