package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.entity.Train;
import com.example.ticket.mapper.TrainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @create 2023/10/3 17:39
 */
@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements TrainService{

    @Override
    public Integer getTrainType(Long trainId) {
        Train train = baseMapper.selectById(trainId);
        return train.getTrainType();
    }
}
