package com.example.ticket.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/10/15 16:42
 */
@Data
@Builder
public class Route {

    /**
     * 出发站点
     */
    private String startStation;

    /**
     * 目的站点
     */
    private String endStation;

}
