package com.example.user.strategy;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.strategy.login.PhoneLoginStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 22:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PhoneLoginStrategyTest {

    private PhoneLoginStrategy phoneLoginStrategy;

    @Autowired
    public void setPhoneLoginStrategy(PhoneLoginStrategy phoneLoginStrategyImpl){
        this.phoneLoginStrategy = phoneLoginStrategyImpl;
    }

    @Test
    public void getUserName(){
        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .loginName("15086888572")
                .password("123456")
                .build();

        String userName = phoneLoginStrategy.getUserName(requestParam);
        System.out.println(userName);
    }
}
