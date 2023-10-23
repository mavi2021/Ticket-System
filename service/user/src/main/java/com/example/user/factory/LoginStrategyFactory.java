package com.example.user.factory;

import com.example.user.enums.LoginStrategyEnum;
import com.example.user.strategy.login.LoginStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/14 20:19
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class LoginStrategyFactory {

    private final ApplicationContext applicationContext;

    public LoginStrategy getLoginStrategy(Integer loginType) {
        return (LoginStrategy) applicationContext.getBean(LoginStrategyEnum.findBeanNameByLoginType(loginType));
    }
}


