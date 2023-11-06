package com.example.ticket.handler;

public interface AbstractChainHandler<T> {

    void handler(T requestParam);


    String getMark();

}
