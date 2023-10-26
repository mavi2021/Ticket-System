package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @create 2023/9/20 20:16
 */
@Data
@Builder
@TableName("t_passenger_0")
public class Passenger extends BaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号码
     */
    private String idCard;

    /**
     * 优惠类型
     */
    private Integer discountType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 添加日期
     */
    private Date createDate;

    /**
     * 审核状态
     */
    private Integer verifyStatus;
}
