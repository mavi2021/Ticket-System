package com.example.pay.dto.base.refund;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/24 21:37
 */
@Data
@Builder
public class RefundResponse {
    /**
     * 支付状态
     */
    private Integer status;
}
