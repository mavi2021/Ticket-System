package com.example.ticket.context;

import com.example.ticket.common.enums.TicketChainMarkEnum;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import lombok.Getter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AbstractChainContextHolderTest {

    @Getter
    private AbstractChainContextHolder<PurchaseTicketReqDTO> abstractChainContextHolder;

    @Autowired
    public void setAbstractChainContextHolder(AbstractChainContextHolder<PurchaseTicketReqDTO> abstractChainContextHolder){
        this.abstractChainContextHolder = abstractChainContextHolder;
    }

    @Test
    public void handler(){
        PurchaseTicketReqDTO rr = PurchaseTicketReqDTO.builder()
                .build();
        abstractChainContextHolder.handler(TicketChainMarkEnum.TRAIN_PURCHASE_TICKET_FILTER.name(), rr);
    }
}
