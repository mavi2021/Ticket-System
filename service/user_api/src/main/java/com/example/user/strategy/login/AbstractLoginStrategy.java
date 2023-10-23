package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;

/**
 * @create 2023/10/14 21:33
 */
public class AbstractLoginStrategy implements LoginStrategy {
    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return null;
    }
}
