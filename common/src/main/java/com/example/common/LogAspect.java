package com.example.common;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @create 2023/6/16 18:59
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.example.common.Log)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            // 获取方法名
            String methodName = joinPoint.getSignature().getName();
            // 获取执行对象
            Class<?> object = joinPoint.getTarget().getClass();
            String objectName = object.getSimpleName();
            // 获取参数列表
            Object[] args = joinPoint.getArgs();
            // 获取更新之前的用户信息
            Long id = null;
            if(!BeanUtil.isEmpty(args)){
                if(args[0] instanceof Long){
                    id = (Long) args[0];
                }else {
                    Field field = args[0].getClass().getSuperclass().getDeclaredField("id");
                    field.setAccessible(true);  // 设置可访问私有属性
                    id = (Long) field.get(args[0]);
                }
            }

            log.info("开始执行{}对象的{}方法，对象ID: {}", objectName, methodName, id);
            // 调用目标方法
            result = joinPoint.proceed();
            // 记录操作日志net
            if (result instanceof Result) {
                log.info("执行成功");
            }
        } catch (Throwable e) {
            // 记录方法调用异常的日志
            log.error("执行失败：" + e.getMessage());
            throw e;
        }
        return result;
    }
}
