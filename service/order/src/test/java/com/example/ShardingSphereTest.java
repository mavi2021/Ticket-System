package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.order.entity.Order;
import com.example.order.service.OrderServiceImpl;
import org.apache.shardingsphere.driver.ShardingSphereDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.Properties;

/**
 * @date 2023/11/20 15:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingSphereTest {

    @Resource
    private OrderServiceImpl orderService;

    @Test
    public void testShardingSphere() throws SQLException, ClassNotFoundException {
        ShardingSphereDriver shardingSphereDriver = new ShardingSphereDriver();
        Connection connect = shardingSphereDriver.connect("jdbc:shardingsphere://127.0.0.1:3306/12306?serverTimezone=Asia/Shanghai&useSSL=false", new Properties());
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_order_0");
        System.out.println(resultSet);
    }

    @Test
    public void testSelect(){
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery(Order.class).eq(Order::getOrderSn, "1111");
        Order order = orderService.getOne(orderLambdaQueryWrapper);
        System.out.println(order);
    }
}
