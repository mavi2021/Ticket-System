package com.example.enums;

import com.example.ticket.common.enums.SeatSelectStrategyEnum;
import org.junit.Test;

public class SelectStrategyEnumTest {

    @Test
    public void getBeanNameByType(){
        String beanNameByType = SeatSelectStrategyEnum.getBeanNameByType(SeatSelectStrategyEnum.DESIGNATED.getType());
        System.out.println(beanNameByType);
    }
}
