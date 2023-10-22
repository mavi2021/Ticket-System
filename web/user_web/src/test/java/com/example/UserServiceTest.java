package com.example;

import com.example.common.Result;
import com.example.user.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

//    @Autowired
    @Reference(version = "1.0.0",check = false)
    private UserService userService;

//    @Test
//    public void queryTest(){
//        Result<Object> userById = userService.getUserById(4L);
//        System.out.println(userById);
//    }


}