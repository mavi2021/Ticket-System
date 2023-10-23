package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/14 19:28
 */
@Component
public class UsernameLoginStrategy extends AbstractLoginStrategy{
    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return requestParam.getLoginName();
    }
}
