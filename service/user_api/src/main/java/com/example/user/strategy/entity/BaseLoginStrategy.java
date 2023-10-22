package com.example.user.strategy.entity;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.strategy.AbstractLoginStrategy;

/**
 * @create 2023/10/14 21:33
 */
public class BaseLoginStrategy implements AbstractLoginStrategy {
    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return null;
    }
}
