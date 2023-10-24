package com.example.pay.executor;

import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.dto.base.pay.PayResponse;
import com.example.pay.dto.base.refund.RefundRequest;
import com.example.pay.dto.base.refund.RefundResponse;

/**
 * @create 2023/10/23 22:15
 */
public interface AbstractPayStrategyExecutor {

    PayResponse pay(PayRequest payRequest);
    RefundResponse refund(RefundRequest refundRequest);
}
