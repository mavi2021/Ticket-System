package com.example.user.service;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserRegisterReqDTO;
import com.example.user.dto.resp.UserLoginRespDTO;
import com.example.user.dto.resp.UserRegisterRespDTO;
import com.example.user.service.UserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 17:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserLoginTest {

    @Resource
    private UserLoginService userLoginService;

    @Test
    public void login() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .loginName("1060830224@qq.com")
                .LoginType(2)
                .password("123456")
                .build();
        UserLoginRespDTO login = userLoginService.login(requestParam);
        System.out.println(login);
    }

    @Test
    public void register(){
        UserRegisterReqDTO build = UserRegisterReqDTO.builder()
                .userType(1)
                .username("fsss")
                .password("123456")
                .realName("lisi")
                .phone("123832763")
                .mail("123191@qq.com")
                .idType(1)
                .idCard("3728281112")
                .build();
        UserRegisterRespDTO register = userLoginService.register(build);
        System.out.println(register);
    }

}
