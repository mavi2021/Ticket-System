package com.example.order.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @create 2023/10/20 21:47
 */
public enum OrderStatusTypeEnum {

    PENDING_PAY(0, Collections.singletonList(OrderStatusEnum.PENDING_PAYMENT.getStatus())),

    PAID(1, Arrays.asList(OrderStatusEnum.ALREADY_PAID.getStatus(), OrderStatusEnum.PARTIAL_REFUND.getStatus(), OrderStatusEnum.FULL_REFUND.getStatus())),

    COMPLETED(2, Collections.singletonList(OrderStatusEnum.COMPLETED.getStatus()));

    @Getter
    private final Integer payType;

    @Getter
    private final List<Integer> paymentList;

    OrderStatusTypeEnum(int payType, List<Integer> paymentList) {
        this.payType = payType;
        this.paymentList = paymentList;
    }

    /**
     * 根据编码查找类型列表
     */
    public static List<Integer> getPaymentListByType(Integer payType){
        return Arrays.stream(values()).filter(each-> Objects.equals(each.getPayType(), payType))
                .findFirst().map(OrderStatusTypeEnum::getPaymentList).orElse(null);
    }
}
