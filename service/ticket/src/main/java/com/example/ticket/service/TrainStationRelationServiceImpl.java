package com.example.ticket.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.dto.resp.TrainStationRelationRespDTO;
import com.example.ticket.entity.TrainStationRelation;
import com.example.ticket.mapper.TrainStationRelationMapper;
import org.springframework.stereotype.Service;

@Service
public class TrainStationRelationServiceImpl extends ServiceImpl<TrainStationRelationMapper, TrainStationRelation> implements TrainStationRelationService{
    @Override
    public TrainStationRelationRespDTO queryTrainStatinRelation(String trainId, String departure, String arrival) {
        LambdaQueryWrapper<TrainStationRelation> stationRelationLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationRelation.class)
                .eq(TrainStationRelation::getTrainId, trainId)
                .eq(TrainStationRelation::getDeparture, departure)
                .eq(TrainStationRelation::getArrival, arrival);
        TrainStationRelation trainStationRelation = baseMapper.selectOne(stationRelationLambdaQueryWrapper);
        return BeanUtil.copyProperties(trainStationRelation, TrainStationRelationRespDTO.class);
    }
}
