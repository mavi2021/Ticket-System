package com.example.toolkit.enums;

import com.example.ticket.common.enums.SelectStrategyEnum;
import org.junit.Test;

public class SelectStrategyEnumTest {

    @Test
    public void getBeanNameByType(){
        String beanNameByType = SelectStrategyEnum.getBeanNameByType(SelectStrategyEnum.DESIGNATED.getType());
        System.out.println(beanNameByType);
    }
}
