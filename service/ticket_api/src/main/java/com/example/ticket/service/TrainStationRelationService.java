package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.resp.TrainStationRelationRespDTO;
import com.example.ticket.entity.TrainStationRelation;

public interface TrainStationRelationService extends IService<TrainStationRelation> {

    TrainStationRelationRespDTO queryTrainStatinRelation(String trainId, String departure, String arrival);
}
