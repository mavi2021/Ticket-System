package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.bases.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/14 21:59
 */
@Data
@Builder
@TableName("t_user_phone_0")
public class UserPhone extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
