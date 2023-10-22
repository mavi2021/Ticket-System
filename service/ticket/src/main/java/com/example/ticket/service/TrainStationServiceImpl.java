package com.example.ticket.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.dto.req.RegionStationQueryReqDTO;
import com.example.ticket.dto.resp.RegionStationQueryRespDTO;
import com.example.ticket.dto.resp.StationQueryRespDTO;
import com.example.ticket.dto.resp.TrainStationQueryRespDTO;
import com.example.ticket.entity.Route;
import com.example.ticket.entity.TrainStation;
import com.example.ticket.mapper.TrainStationMapper;
import com.example.ticket.toolkit.StationCalculateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @create 2023/9/24 10:00
 */
@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class TrainStationServiceImpl extends ServiceImpl<TrainStationMapper, TrainStation> implements TrainStationService{

    @Override
    public List<TrainStationQueryRespDTO> listTrainStationQuery(String trainId) {
        LambdaQueryWrapper<TrainStation> trainStationLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStation.class).eq(TrainStation::getTrainId, trainId);
        List<TrainStation> trainStationList = baseMapper.selectList(trainStationLambdaQueryWrapper);;
        return trainStationList.stream().map(each ->BeanUtil.copyProperties(each, TrainStationQueryRespDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<Route> listTrainStationRoute(String trainId, String departure, String arrival) {
        LambdaQueryWrapper<TrainStation> trainStationLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStation.class)
                .eq(TrainStation::getTrainId, trainId)
                .orderByAsc(TrainStation::getSequence);
        List<TrainStation> trainStationList = baseMapper.selectList(trainStationLambdaQueryWrapper);
        List<String> departureList = trainStationList.stream().map(TrainStation::getDeparture).collect(Collectors.toList());
        return StationCalculateUtil.throughStation(departureList, departure, arrival);
    }

    @Override
    public List<Route> listTakeoutTrainStationRoute(String trainId, String departure, String arrival) {
        LambdaQueryWrapper<TrainStation> trainStationLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStation.class)
                .eq(TrainStation::getTrainId, trainId)
                .orderByAsc(TrainStation::getSequence);
        List<TrainStation> trainStationList = baseMapper.selectList(trainStationLambdaQueryWrapper);
        List<String> departureList = trainStationList.stream().map(TrainStation::getDeparture).collect(Collectors.toList());
        return StationCalculateUtil.takeoutStation(departureList, departure, arrival);
    }

}
