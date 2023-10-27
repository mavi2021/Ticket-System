package com.example.ticket.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/27 16:30
 */
@Data
@Builder
public class SeatDistributeRespDTO {

    /**
     * 乘车人 ID
     */
    private String passengerId;

    /**
     * 座位类型
     */
    private Integer seatType;

    /**
     * 车厢号
     */
    private String carriageNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 车票价格
     */
    private Integer price;

}
