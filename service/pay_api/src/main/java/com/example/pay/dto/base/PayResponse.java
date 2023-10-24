package com.example.pay.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayResponse {

    /**
     * 调用支付返回信息
     */
    private String body;
}
