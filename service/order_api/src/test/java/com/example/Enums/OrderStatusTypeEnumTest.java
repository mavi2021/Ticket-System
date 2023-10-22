package com.example.Enums;

import com.example.order.enums.OrderStatusTypeEnum;
import org.junit.Test;

import java.util.List;

/**
 * @create 2023/10/20 22:26
 */
public class OrderStatusTypeEnumTest {
    @Test
    public void getPaymentListByType(){
        List<Integer> paymentListByType = OrderStatusTypeEnum.getPaymentListByType(2);
        System.out.println(paymentListByType);
    }
}
