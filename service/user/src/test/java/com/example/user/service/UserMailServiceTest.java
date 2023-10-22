package com.example.user.service;

import com.example.user.dto.req.UserMailAddDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @create 2023/10/15 0:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMailServiceTest {

    private UserMailService userMailService;
    @Autowired
    void setUserMailService(UserMailService userMailServiceImpl){
        this.userMailService = userMailServiceImpl;
    }


    @Test
    public void add(){
        UserMailAddDTO zzzzz = UserMailAddDTO.builder()
                .mail("22332322@qq.com")
                .username("sssss")
                .build();
        userMailService.add(zzzzz);
    }

    @Test
    public void deleteByUsername(){
        userMailService.deleteByUsername("sawa");
    }
}
