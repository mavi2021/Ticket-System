package com.example.ticket.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckTicketRespDTO {

    private String passengerName;
    private String trainNumber;
    private String departure;
    private String arrival;
    private String carriageNumber;
    private String seatNumber;
    private String discountType;
    private Boolean checkResult;
}
