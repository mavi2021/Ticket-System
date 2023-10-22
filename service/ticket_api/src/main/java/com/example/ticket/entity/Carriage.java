package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/16 1:24
 */
@Data
@Builder
@TableName("t_carriage")
public class Carriage extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 车厢类型
     */
    private Integer carriageType;

    /**
     * 座位数
     */
    private Integer seatCount;
}
