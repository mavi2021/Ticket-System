package com.example.user.enums;

import org.junit.Test;

/**
 * @create 2023/10/14 20:56
 */

public class LoginStrategyEnumTest {

    @Test
    public void findNameByType(){
        String nameByType = LoginStrategyEnum.findNameByType(1);
        System.out.println(nameByType);
    }
}
