package com.example.ticket.factory;

import com.example.ticket.strategy.SeatSelectStrategy;

public interface AbstractSeatSelectStrategyFactory {

    SeatSelectStrategy getSeatSelectStrategy(Integer type);

}
