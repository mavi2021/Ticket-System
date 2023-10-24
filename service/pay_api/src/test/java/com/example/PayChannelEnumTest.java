package com.example;

import com.example.pay.enums.PayChannelEnum;
import org.junit.Test;

/**
 * @create 2023/10/23 21:08
 */
public class PayChannelEnumTest {

    @Test
    public void getClassLocationByPayType(){
        String classLocationByPayType = PayChannelEnum.getBeanNameByPayType(0);
        System.out.println(classLocationByPayType);
    }
}
