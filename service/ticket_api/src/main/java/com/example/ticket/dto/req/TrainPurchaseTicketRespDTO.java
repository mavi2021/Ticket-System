package com.example.ticket.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 * 列车购票出参
 */
@Data
@Builder
public class TrainPurchaseTicketRespDTO {

    /**
     * 乘车人 ID
     */
    private String passengerId;

    /**
     * 乘车人姓名
     */
    private String realName;

    /**
     * 乘车人证件类型
     */
    private Integer idType;

    /**
     * 乘车人证件号
     */
    private String idCard;

    /**
     * 乘车人手机号
     */
    private String phone;

    /**
     * 车票优惠类型 0：成人票 1：儿童票 2：学生票 3：残疾军人票
     */
    private Integer discountType;

    /**
     * 坐席 ID
     */
    private String seatId;

    /**
     * 席别类型
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
     * 座位金额
     */
    private Integer price;
}
