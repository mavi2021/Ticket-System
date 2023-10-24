package com.example;

import com.example.pay.enums.PayChannelEnum;
import org.junit.Test;

/**
 * @create 2023/10/23 21:08
 */
public class PayChannelEnumTest {

    @Test
    public void getBeanNameByPayChannel(){
        String classLocationByPayType = PayChannelEnum.getBeanNameByPayChannel(1);
        System.out.println(classLocationByPayType);
    }
}
