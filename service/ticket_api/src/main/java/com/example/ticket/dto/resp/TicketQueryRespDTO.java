package com.example.ticket.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketQueryRespDTO {

    private String passengerName;
    private String trainNumber;
    private String discountType;
    private String departure;
    private String arrival;
    private String carriageNumber;
    private String seatNumber;
}
