package com.example.ticket.handler.purchase;

import cn.hutool.core.util.StrUtil;
import com.example.common.exception.ClientException;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TrainPurchaseTicketParamNotNullChainHandler implements TrainPurchaseTicketChainHandler<PurchaseTicketReqDTO> {

    @Override
    public void handler(PurchaseTicketReqDTO requestParam) {
        if(StrUtil.isBlank(requestParam.getTrainId())){
            throw new ClientException("列车id不能为空");
        }
        if(StrUtil.isBlank(requestParam.getDeparture())){
            throw new ClientException("出发地不能为空");
        }
        if(StrUtil.isBlank(requestParam.getArrival())){
            throw new ClientException("到达地不能为空");
        }
        if(Objects.isNull(requestParam.getSeatSelectStrategyType())){
            throw new ClientException("选座策略类型不能为空");
        }
        requestParam.getPassengers().forEach(each->{
            if(StrUtil.isBlank(each.getPassengerId())){
                throw new ClientException("乘车人不能为空");
            }
            if(Objects.isNull(each.getSeatType())){
                throw new ClientException("座位类型不能为空");
            }
        });
    }
}
