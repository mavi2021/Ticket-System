package com.example;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.common.Result;
import com.example.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    //    @Autowired
    @Reference(version = "1.0.0",check = false)
    private OrderService orderService;

    @Test
    public void queryTest(){
//        Result<Object> orderById = orderService.getOrderById(4L);
//        System.out.println(orderById);
    }


}