package com.example.pay.enums;

import lombok.Getter;

/**
 * @create 2023/10/22 20:55
 */
public enum PayChannelEnum {
    /**
     * 支付宝
     */
    ALI_PAY(0, "ALI_PAY", "支付宝");

    @Getter
    private final Integer code;

    @Getter
    private final String name;

    @Getter
    private final String value;

    PayChannelEnum(Integer code, String name, String value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }
}
