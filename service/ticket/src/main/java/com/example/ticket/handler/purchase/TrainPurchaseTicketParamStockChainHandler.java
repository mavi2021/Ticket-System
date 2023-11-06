package com.example.ticket.handler.purchase;

import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainPurchaseTicketParamStockChainHandler implements TrainPurchaseTicketChainHandler<PurchaseTicketReqDTO>{
    @Override
    public void handler(PurchaseTicketReqDTO requestParam) {
        System.out.println("检查余票库存");
    }
}
