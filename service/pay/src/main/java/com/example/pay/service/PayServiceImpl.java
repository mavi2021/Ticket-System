package com.example.pay.service;

import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.req.RefundReqDTO;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.dto.resp.RefundRespDTO;

/**
 * @create 2023/10/22 21:43
 */
public class PayServiceImpl implements PayService{
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
