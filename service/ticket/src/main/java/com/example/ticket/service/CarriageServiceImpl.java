package com.example.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.entity.Carriage;
import com.example.ticket.mapper.CarriageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @create 2023/10/16 1:27
 */
@Service
public class CarriageServiceImpl extends ServiceImpl<CarriageMapper, Carriage> implements CarriageService{
    @Override
    public List<String> listCarriageNumber(String trainId, Integer carriageType) {
        LambdaQueryWrapper<Carriage> carriageLambdaQueryWrapper = Wrappers.lambdaQuery(Carriage.class)
                .eq(Carriage::getTrainId, trainId)
                .eq(Carriage::getCarriageType, carriageType);
//                .eq(Carriage::getCarriageType, carriageType).select(Carriage::getCarriageNumber);
        List<Carriage> carriages = baseMapper.selectList(carriageLambdaQueryWrapper);
        return carriages.stream().map(Carriage::getCarriageNumber).collect(Collectors.toList());
    }
}
