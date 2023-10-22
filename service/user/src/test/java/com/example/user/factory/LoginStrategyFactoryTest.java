package com.example.user.factory;

import com.example.user.dto.req.UserLoginReqDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 20:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginStrategyFactoryTest {

    @Resource
    private LoginStrategyFactory loginStrategyFactory;

    @Test
    public void getLoginStrategyByType(){
//        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
//                .LoginType(2)
//                .loginName("1060830224@qq.com")
//                .build();

        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .LoginType(1)
                .loginName("15086888572")
                .build();
        Object loginStrategyByType = loginStrategyFactory.getLoginStrategy(requestParam.getLoginType());
        System.out.println(loginStrategyByType);
    }

}
