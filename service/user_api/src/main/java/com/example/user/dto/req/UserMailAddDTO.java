package com.example.user.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/15 0:35
 */
@Data
@Builder
public class UserMailAddDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mail;

}
