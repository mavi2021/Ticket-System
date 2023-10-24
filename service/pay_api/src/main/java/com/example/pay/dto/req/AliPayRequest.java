package com.example.pay.dto.req;

import com.example.pay.dto.base.pay.AbstractPayRequest;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public final class AliPayRequest extends AbstractPayRequest {

    /**
     * 商户订单号
     * 由商家自定义，64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复
     */
    private String outOrderSn;

    /**
     * 订单标题
     * 注意：不可使用特殊字符，如 /，=，& 等
     */
    private String subject;

    /**
     * 交易凭证号
     */
    private String tradeNo;

}
