package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @create 2023/9/24 9:49
 */
@Data
@Builder
@TableName("t_train_station")
public class TrainStation extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车次id
     */
    private Long trainId;

    /**
     * 车站id
     */
    private Long stationId;

    /**
     * 站点顺序
     */
    private String sequence;

    /**
     * 出发站点
     */
    private String departure;

    /**
     * 到达站点
     */
    private String arrival;

    /**
     * 起始城市
     */
    private String startRegion;

    /**
     * 终点城市
     */
    private String endRegion;

    /**
     * 到站时间
     */
    private Date arrivalTime;

    /**
     * 出站时间
     */
    private Date departureTime;

    /**
     * 停留时间，单位分
     */
    private Integer stopoverTime;
}
