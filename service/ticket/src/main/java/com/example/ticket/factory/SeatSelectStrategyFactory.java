package com.example.ticket.factory;


import com.example.ticket.common.enums.SelectStrategyEnum;
import com.example.ticket.strategy.SeatSelectStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SeatSelectStrategyFactory implements AbstractSeatSelectStrategyFactory {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public SeatSelectStrategy getSeatSelectStrategy(Integer type) {
        return (SeatSelectStrategy) applicationContext.getBean(SelectStrategyEnum.getBeanNameByType(type));
    }

}
