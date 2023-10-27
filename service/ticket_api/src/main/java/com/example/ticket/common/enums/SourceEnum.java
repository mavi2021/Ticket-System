package com.example.ticket.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @create 2023/10/27 20:26
 */
@RequiredArgsConstructor
public enum SourceEnum {

    /**
     * 互联网购票
     */
    INTERNET(0),

    /**
     * 线下窗口购票
     */
    OFFLINE(1);

    @Getter
    private final Integer code;
}
