package com.example.user.strategy;

import com.example.user.dto.req.UserLoginReqDTO;

/**
 * @create 2023/10/14 19:24
 */
public interface AbstractLoginStrategy {

    String getUserName(UserLoginReqDTO requestParam);

}
