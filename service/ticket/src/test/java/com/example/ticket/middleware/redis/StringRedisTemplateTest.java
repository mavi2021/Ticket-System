package com.example.ticket.middleware.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @date 2023/11/7 12:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StringRedisTemplateTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void stringRedisTemplateSetTest(){
        stringRedisTemplate.opsForValue().set("test1", "哈哈", 3, TimeUnit.MINUTES);
    }

    @Test
    public void stringRedisTemplateGetTest(){
        String test1 = stringRedisTemplate.opsForValue().get("test1");
        System.out.println(test1);
    }

    @Test
    public void redisTemplateSetTest(){
        redisTemplate.opsForValue().set("test1", "haha", 3, TimeUnit.MINUTES);
    }
}
