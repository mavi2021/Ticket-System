package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @create 2023/9/25 21:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_seat")
public class Seat extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 列车id
     */
    private Long trainId;

    /**
     * 车厢号
     */
    private String carriageNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 座位类型
     */
    private Integer seatType;

    /**
     * 起始站
     */
    private String startStation;

    /**
     * 终点站
     */
    private String endStation;

    /**
     * 座位状态
     */
    private Integer seatStatus;

    /**
     * 车票价格
     */
    private Integer price;

    /**
     * 坐席按车厢分组数量
     */
    @TableField(value = "count(*)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER, select = false)
    private Integer CarriageGroupCount;

}
