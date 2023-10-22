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
    private Integer type;

    @Getter
    private String strategyName;

    LoginStrategyEnum(Integer loginType, String loginStrategy) {
        this.type = loginType;
        this.strategyName = loginStrategy;
    }

    public static String findNameByType(Integer loginType){
        LoginStrategyEnum[] values = LoginStrategyEnum.values();
        LoginStrategyEnum loginStrategyEnum = Arrays.stream(values).filter(each -> Objects.deepEquals(each.type, loginType))
                .findFirst().orElse(null);
        assert loginStrategyEnum != null;
        return loginStrategyEnum.strategyName;
    }
}
