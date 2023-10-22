package com.example.user.service;

import com.example.user.dto.req.UserDeletionReqDTO;
import com.example.user.dto.resp.UserDeletionRespDTO;
import com.example.user.entity.UserDeletion;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @create 2023/10/15 1:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDeletionServiceTest {

    @Autowired
    private UserDeletionService userDeletionService;

//    @Autowired
//    public UserDeletionServiceTest(UserDeletionService service){
//        this.userDeletionService = service;
//    }

    @Test
    public void selectUserDeletion(){
        UserDeletionReqDTO sjsj = UserDeletionReqDTO.builder().username("sjsj").build();
//        userDeletionService.deletionUser(sjsj);
        UserDeletionRespDTO userDeletion = userDeletionService.selectUserDeletion(sjsj);
        System.out.println(userDeletion);
    }

    @Test
    public void deletionUser(){
        UserDeletionReqDTO fsss = UserDeletionReqDTO.builder()
                .username("fsss")
                .build();
        userDeletionService.deletionUser(fsss);
    }
}
