package com.example.pay.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pay.dto.base.pay.PayRequest;
import com.example.pay.dto.base.pay.PayResponse;
import com.example.pay.dto.base.refund.RefundRequest;
import com.example.pay.dto.base.refund.RefundResponse;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.resp.PayInfoRespDTO;
import com.example.pay.dto.resp.PayRespDTO;
import com.example.pay.dto.resp.RefundRespDTO;
import com.example.pay.entity.Pay;
import com.example.pay.enums.TradeStatusEnum;
import com.example.pay.executor.PayStrategyExecutor;
import com.example.pay.mapper.PayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * @create 2023/10/22 21:43
 */
//@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0", protocol = "dubbo")
@RequiredArgsConstructor
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService{

    private final PayStrategyExecutor payStrategyExecutor;

    @Override
    public PayRespDTO commonPay(PayRequest requestParam) {
        PayResponse payResponse = payStrategyExecutor.pay(requestParam);
        Pay pay = BeanUtil.copyProperties(requestParam, Pay.class);
        pay.setPaySn(String.valueOf(UUID.randomUUID()));
        pay.setStatus(TradeStatusEnum.WAIT_BUYER_PAY.getTradeCode());
        pay.setTotalAmount(requestParam.getTotalAmount().multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_UP).intValue());
        baseMapper.insert(pay);
        return BeanUtil.copyProperties(payResponse, PayRespDTO.class);
    }

    @Override
    public void callbackPay(PayCallbackReqDTO requestParam) {
        LambdaUpdateWrapper<Pay> payLambdaUpdateWrapper = Wrappers.lambdaUpdate(Pay.class).eq(Pay::getOrderSn, requestParam.getOrderSn());
        Pay updatePay =  Pay.builder()
                .tradeNo(requestParam.getTradeNo())
                .status(requestParam.getStatus())
                .payAmount(requestParam.getPayAmount())
                .gmtPayment(requestParam.getGmtPayment())
                .build();
        baseMapper.update(updatePay, payLambdaUpdateWrapper);
    }

    @Override
    public PayInfoRespDTO getPayInfoByOrderSn(String orderSn) {
        LambdaQueryWrapper<Pay> payLambdaQueryWrapper = Wrappers.lambdaQuery(Pay.class)
                .eq(Pay::getOrderSn, orderSn);
        Pay pay = baseMapper.selectOne(payLambdaQueryWrapper);
        return BeanUtil.copyProperties(pay, PayInfoRespDTO.class);
    }

    @Override
    public PayInfoRespDTO getPayInfoByPaySn(String paySn) {
        LambdaQueryWrapper<Pay> payLambdaQueryWrapper = Wrappers.lambdaQuery(Pay.class)
                .eq(Pay::getPaySn, paySn);
        Pay pay = baseMapper.selectOne(payLambdaQueryWrapper);
        return BeanUtil.copyProperties(pay, PayInfoRespDTO.class);
    }

    @Override
    public RefundRespDTO commonRefund(RefundRequest requestParam) {
        RefundResponse refundResponse = payStrategyExecutor.refund(requestParam);
        LambdaUpdateWrapper<Pay> payLambdaUpdateWrapper = Wrappers.lambdaUpdate(Pay.class)
                .eq(Pay::getOrderSn, requestParam.getOrderSn());
        Pay updatePay = Pay.builder().status(refundResponse.getStatus()).build();
        baseMapper.update(updatePay, payLambdaUpdateWrapper);
        return BeanUtil.copyProperties(refundResponse, RefundRespDTO.class);
    }
}
