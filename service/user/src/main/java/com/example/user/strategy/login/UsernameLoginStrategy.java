package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.strategy.AbstractLoginStrategy;
import com.example.user.strategy.entity.BaseLoginStrategy;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/14 19:28
 */
@Component
public class UsernameLoginStrategy extends BaseLoginStrategy{
    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return requestParam.getLoginName();
    }
}
