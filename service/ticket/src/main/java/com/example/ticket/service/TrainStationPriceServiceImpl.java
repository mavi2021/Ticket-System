package com.example.ticket.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.resp.TrainStationPriceRespDTO;
import com.example.ticket.entity.TrainStationPrice;
import com.example.ticket.mapper.TrainStationPriceMapper;
import org.springframework.stereotype.Service;

/**
 * @create 2023/10/27 15:10
 */
@Service
public class TrainStationPriceServiceImpl extends ServiceImpl<TrainStationPriceMapper, TrainStationPrice> implements TrainStationPriceService{
    @Override
    public TrainStationPriceRespDTO queryTrainStationPrice(String trainId, String seatType, String departure, String arrival) {
        LambdaQueryWrapper<TrainStationPrice> trainStationPriceLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationPrice.class)
                .eq(TrainStationPrice::getTrainId, trainId)
                .eq(TrainStationPrice::getSeatType, seatType)
                .eq(TrainStationPrice::getDeparture, departure)
                .eq(TrainStationPrice::getArrival, arrival);
        return BeanUtil.copyProperties(baseMapper.selectOne(trainStationPriceLambdaQueryWrapper), TrainStationPriceRespDTO.class);
    }
}
