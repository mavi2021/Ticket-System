package com.example.ticket.service;

import java.util.List;

/**
 * @create 2023/10/16 1:27
 */
public interface CarriageService {
    /**
     * 查询列车车厢号集合
     *
     * @param trainId      列车 ID
     * @param carriageType 车厢类型
     * @return 车厢号集合
     */
    List<String> listCarriageNumber(String trainId, Integer carriageType);
}
