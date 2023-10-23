package com.example.pay.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @create 2023/10/22 20:55
 */
public enum PayChannelEnum {
    /**
     * 支付宝
     */
    ALI_PAY(0, "ALI_PAY", "支付宝", "aliPayStrategy"),

    WX_PAY(1, "WX_PAY", "微信", "wxPayStrategy");

    @Getter
    private final Integer payType;

    @Getter
    private final String name;

    @Getter
    private final String value;

    @Getter
    private final String beanName;

    PayChannelEnum(Integer payType, String name, String value, String beanName) {
        this.payType = payType;
        this.name = name;
        this.value = value;
        this.beanName = beanName;
    }

    public static String getBeanNameByPayType(Integer payType){
       return Arrays.stream(values())
               .filter(each-> Objects.equals(each.getPayType(), payType))
               .findFirst()
               .map(PayChannelEnum::getBeanName)
               .orElse(null);
    }


}
