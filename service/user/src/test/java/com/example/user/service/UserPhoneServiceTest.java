package com.example.user.service;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserPhoneAddDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 22:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserPhoneServiceTest {

    @Resource
    private UserPhoneService userPhoneService;

    @Test
    public void selectUserByLoginPhone(){
        UserLoginReqDTO requestParam = UserLoginReqDTO.builder()
                .loginName("15086888572")
                .password("123456")
                .build();

        String username = userPhoneService.selectUserByLoginPhone(requestParam);
        System.out.println(username);
    }

    @Test
    public void add(){
        UserPhoneAddDTO zoey = UserPhoneAddDTO.builder()
                .phone("13578892029")
                .username("zoey")
                .build();
        userPhoneService.add(zoey);
    }

    @Test
    public void deleteByUsername(){
        userPhoneService.deleteByUsername("aakakskj");
    }
}
