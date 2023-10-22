package com.example.user.executor;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.enums.LoginStrategyEnum;
import com.example.user.factory.LoginStrategyFactory;
import com.example.user.strategy.entity.BaseLoginStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 20:44
 */
@Component
public class LoginStrategyExecutor {

    @Resource
    private LoginStrategyFactory loginStrategyFactory;

    public String getUsername(UserLoginReqDTO requestParam){
        Integer loginType = requestParam.getLoginType();
        BaseLoginStrategy loginStrategy = loginStrategyFactory.getLoginStrategy(loginType);
        return loginStrategy.getUserName(requestParam);
    }

}
