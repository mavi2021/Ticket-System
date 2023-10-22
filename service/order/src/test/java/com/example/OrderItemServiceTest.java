package com.example;

import com.example.order.entity.OrderItem;
import com.example.order.service.OrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @create 2023/10/20 19:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderItemServiceTest {

    @Resource
    private OrderItemService orderItemService;

    @Test
    public void selectOrderItemList(){
        List<OrderItem> orderItems = orderItemService.selectOrderItemList("1111");
        System.out.println(orderItems);
    }


}
