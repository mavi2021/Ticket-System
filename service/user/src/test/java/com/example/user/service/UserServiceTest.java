package com.example.user.service;

import com.example.user.dto.req.UserRegisterReqDTO;
import com.example.user.dto.req.UserUpdateReqDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

//    @Autowired
//    @Reference(version = "1.0.0",check = false)
    @Resource
    private UserService userService;

//    @Test
//    public void queryTest(){
//        Result<Object> userById = userService.getUserById(3L);
//        System.out.println(userById);
//    }
//
//    @Test
//    public void deleteTest(){
//        Result<Object> userById = userService.deleteById(4L);
//        System.out.println(userById);
//    }

    @Test
    public void queryUserByUserId(){
        UserQueryRespDTO userQueryRespDTO = userService.queryUserByUserId(1L);
        System.out.println(userQueryRespDTO);
    }

    @Test
    public void queryUserByUsername(){
        UserQueryRespDTO userQueryRespDTO = userService.queryUserByUsername("sjsj");
        System.out.println(userQueryRespDTO);
    }

    @Test
    public void update(){
        UserUpdateReqDTO requestParm = UserUpdateReqDTO.builder()
                .username("joey")
                .address("上海")
                .build();
        userService.update(requestParm);
    }


    @Test
    public void add(){
        UserRegisterReqDTO userRegisterReqDto = UserRegisterReqDTO.builder()
                .userType(1)
                .idCard("333")
                .username("jjj")
                .password("123123")
                .build();
        userService.add(userRegisterReqDto);
    }

    @Test
    public void deleteByUsername(){
        userService.deleteByUsername("ssaaa");
    }
}
