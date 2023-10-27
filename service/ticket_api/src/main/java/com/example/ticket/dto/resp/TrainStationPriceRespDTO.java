package com.example.ticket.dto.resp;

import lombok.Data;

/**
 * @create 2023/10/27 15:09
 */
@Data
public class TrainStationPriceRespDTO {

    /**
     * 车次id
     */
    private Long trainId;

    /**
     * 座位类型
     */
    private Integer seatType;

    /**
     * 出发站点
     */
    private String departure;

    /**
     * 到达站点
     */
    private String arrival;

    /**
     * 车票价格
     */
    private Integer price;
}
