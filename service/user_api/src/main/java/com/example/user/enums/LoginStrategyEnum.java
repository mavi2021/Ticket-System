package com.example.user.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @create 2023/10/14 19:42
 */
public enum LoginStrategyEnum {

    USERNAME(0, "usernameLoginStrategy"),
    PHONE(1, "phoneLoginStrategy"),
    EMAIL(2, "emailLoginStrategy");

    @Getter
    private final Integer loginType;

    @Getter
    private final String beanName;

    LoginStrategyEnum(Integer loginType, String beanName) {
        this.loginType = loginType;
        this.beanName = beanName;
    }

    public static String findBeanNameByLoginType(Integer loginType){
        LoginStrategyEnum[] values = LoginStrategyEnum.values();
        LoginStrategyEnum loginStrategyEnum = Arrays.stream(values).filter(each -> Objects.deepEquals(each.loginType, loginType))
                .findFirst().orElse(null);
        assert loginStrategyEnum != null;
        return loginStrategyEnum.beanName;
    }
}
