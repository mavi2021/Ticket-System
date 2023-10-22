package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Data;

/**
 * @create 2023/9/21 19:32
 */
@Data
//@Builder
@TableName("t_ticket")
public class Ticket extends BaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

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
     * 乘车人 ID
     */
    private String passengerId;

    /**
     * 车票状态
     */
    private Integer ticketStatus;
}
