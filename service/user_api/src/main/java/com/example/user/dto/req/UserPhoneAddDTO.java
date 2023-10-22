package com.example.user.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/15 0:27
 */
@Data
@Builder
public class UserPhoneAddDTO {

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
}
