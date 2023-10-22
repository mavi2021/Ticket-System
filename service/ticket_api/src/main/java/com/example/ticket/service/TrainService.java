package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.entity.Train;

import java.util.List;

/**
 * @create 2023/10/3 17:37
 */
public interface TrainService extends IService<Train> {
    Integer getTrainType(Long trainId);
}
