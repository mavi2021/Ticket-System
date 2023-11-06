package com.example.ticket.handler.purchase;

import com.example.ticket.common.enums.TicketChainMarkEnum;
import com.example.ticket.handler.AbstractChainHandler;

public interface TrainPurchaseTicketChainHandler<T> extends AbstractChainHandler<T> {

    @Override
    default String getMark(){
        return TicketChainMarkEnum.TRAIN_PURCHASE_TICKET_FILTER.name();
    }

}
