package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;

/**
 * @create 2023/10/14 19:24
 */
public interface LoginStrategy {

    String getUserName(UserLoginReqDTO requestParam);

}
