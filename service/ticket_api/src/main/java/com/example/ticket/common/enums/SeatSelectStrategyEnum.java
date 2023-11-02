package com.example.ticket.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public enum SeatSelectStrategyEnum {

    DEFAULT(0, "defaultSeatSelectStrategy"),

    DESIGNATED(1, "designatedSeatSelectStrategy");

    @Getter
    private final Integer type;

    @Getter
    private final String beanName;

    public static String getBeanNameByType(Integer strategyType){
        return Arrays.stream(values())
                .filter(each-> Objects.equals(each.type, strategyType))
                .findFirst()
                .map(SeatSelectStrategyEnum::getBeanName)
                .orElse(null);
    }
}
