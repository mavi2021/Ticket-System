package com.example.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("t_ticket")
public class Ticket extends BaseEntity {

    /**
     * 车票 ID
     */
    private Long id;

    /**
     * 列车号
     */
    private String trainNumber;

    /**
     * 乘车人姓名
     */
    private String passengerName;

    /**
     * 乘车人身份证号
     */
    private String idCard;

    /**
     * 车票优惠类型
     */
    private Integer discountType;

    /**
     * 车票价格
     */
    private Integer ticketPrice;

    /**
     * 车票状态
     */
    private Integer ticketStatus;

    /**
     * 关联乘车人 ID
     */
    private String passengerId;

    /**
     * 关联坐席ID
     */
    private Integer seat_id;

    /**
     * 关联订单号
     */
    private String orderSn;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  修改时间
     */
    private Date updateTime;

    /**
     * 删除标识
     */
    private Integer delFlag;
}
