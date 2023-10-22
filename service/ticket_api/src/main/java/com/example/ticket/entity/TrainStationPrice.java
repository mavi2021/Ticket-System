package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/9/25 11:02
 */
@Data
@Builder
@TableName("t_train_station_price")
public class TrainStationPrice extends BaseEntity {

    /**
     * id
     */
    private Long id;

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
