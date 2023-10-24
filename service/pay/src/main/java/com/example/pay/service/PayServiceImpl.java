package com.example.pay.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.req.RefundReqDTO;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.dto.resp.RefundRespDTO;
import com.example.pay.entity.Pay;
import com.example.pay.executor.PayStrategyExecutor;
import com.example.pay.mapper.PayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @create 2023/10/22 21:43
 */
@Service
@RequiredArgsConstructor
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService{

    private final PayStrategyExecutor payStrategyExecutor;

    @Override
    public PayRespDTO commonPay(PayRequest requestParam) {

        return null;
    }

    @Override
    public void callbackPay(PayCallbackReqDTO requestParam) {

    }

    @Override
    public PayInfoRespDTO getPayInfoByOrderSn(String orderSn) {
        return null;
    }

    @Override
    public PayInfoRespDTO getPayInfoByPaySn(String paySn) {
        return null;
    }

    @Override
    public RefundRespDTO commonRefund(RefundReqDTO requestParam) {
        return null;
    }
}
