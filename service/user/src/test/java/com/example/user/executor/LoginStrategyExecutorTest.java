package com.example.user.executor;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.factory.LoginStrategyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 21:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginStrategyExecutorTest {

    @Resource
    private LoginStrategyExecutor loginStrategyExecutor;

    @Test
    public void getUsernameByName() {
        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .LoginType(0)
                .loginName("joey")
                .build();
        String username = loginStrategyExecutor.getUsername(requestParam);
        System.out.println(username);
    }

    @Test
    public void getUsernameByPhone() {

        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .LoginType(1)
                .loginName("15086888572")
                .build();
        String username = loginStrategyExecutor.getUsername(requestParam);
        System.out.println(username);
    }

    @Test
    public void getUsernameByEmail() {

        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .LoginType(2)
                .loginName("1060830224@qq.com")
                .build();
        String username = loginStrategyExecutor.getUsername(requestParam);
        System.out.println(username);
    }

}
