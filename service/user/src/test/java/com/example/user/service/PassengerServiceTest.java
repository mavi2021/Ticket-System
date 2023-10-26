package com.example.user.service;

import com.example.user.dto.resp.PassengerActualRespDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @create 2023/10/26 14:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;

    @Test
    public void listPassengerQueryByIds(){
        List<PassengerActualRespDTO> passengerActualRespDTOList = passengerService.listPassengerQueryByIds("libai", Arrays.asList(1L, 2L));
        System.out.println(passengerActualRespDTOList);
    }
}
