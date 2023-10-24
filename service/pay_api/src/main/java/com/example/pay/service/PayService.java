package com.example.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pay.dto.base.refund.RefundRequest;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.dto.resp.RefundRespDTO;
import com.example.pay.entity.Pay;

/**
 * @create 2023/10/22 20:50
 */
public interface PayService extends IService<Pay> {
    /**
     * 创建支付单
     *
     * @param requestParam 创建支付单实体
     * @return 支付返回详情
     */
    PayRespDTO commonPay(PayRequest requestParam);

    /**
     * 支付单回调
     *
     * @param requestParam 回调支付单实体
     */
    void callbackPay(PayCallbackReqDTO requestParam);

    /**
     * 跟据订单号查询支付单详情
     *
     * @param orderSn 订单号
     * @return 支付单详情
     */
    PayInfoRespDTO getPayInfoByOrderSn(String orderSn);

    /**
     * 跟据支付流水号查询支付单详情
     *
     * @param paySn 支付单流水号
     * @return 支付单详情
     */
    PayInfoRespDTO getPayInfoByPaySn(String paySn);

    /**
     * 公共退款接口
     *
     * @param requestParam 退款请求参数
     * @return 退款返回详情
     */
    RefundRespDTO commonRefund(RefundRequest requestParam);
}

