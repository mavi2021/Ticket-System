package com.example.user;

import com.example.user.service.UserDeletionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 15:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDeletionTest {

    @Resource
    private UserDeletionService userDeletionService;

    @Test
    public void queryUserDeletionNum(){
        Integer integer = userDeletionService.queryUserDeletionNum(1, "2222");
        System.out.println(integer);
    }
}
