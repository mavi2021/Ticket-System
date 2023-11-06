package com.example.ticket.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckTicketReqDTO {

    private String idCard;

}
