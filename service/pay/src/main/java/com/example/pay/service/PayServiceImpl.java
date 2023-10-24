package com.example.pay.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pay.dto.base.PayRequest;
import com.example.pay.dto.base.PayResponse;
import com.example.pay.dto.req.PayCallbackReqDTO;
import com.example.pay.dto.req.RefundReqDTO;
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
@Service
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
