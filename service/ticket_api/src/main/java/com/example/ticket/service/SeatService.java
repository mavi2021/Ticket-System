package com.example.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.entity.Seat;

import java.util.List;
import java.util.Map;

public interface SeatService extends IService<Seat> {

    /**
     * 查询坐席 ID
     */
    String querySeatId(String trainId, String departure, String arrival, String carriageNum, String SeatNumber);

    /**
     * 查询所有坐席类型的余票
     *
     * @param trainId   列车 ID
     * @param departure 出发站
     * @param arrival   到达站
     * @return  Map<Integer, Integer>
     */
    Map<Integer, Integer> loadAllSeatQuantity(String trainId, String departure, String arrival);

    /**
     * 查询对应坐席类型的余票
     *
     * @param trainId   列车 ID
     * @param seatType  座席类型
     * @param departure    出发站
     * @param arrival      到达站
     * @return  Integer
     */
    Integer loadSeatQuantityBySeatType(String trainId, Integer seatType, String departure, String arrival);

    /**
     * 获取列车车厢中可用的座位集合
     *
     * @param trainId        列车 ID
     * @param carriageNumber 车厢号
     * @param seatType       座位类型
     * @param departure      出发站
     * @param arrival        到达站
     * @return 可用座位集合
     */
    List<String> listAvailableSeat(String trainId, String carriageNumber, Integer seatType, String departure, String arrival);

    /**
     *  查询车厢列表中每个车厢的余票
     *
     * @param trainId   列车 ID
     * @param startStation  出发站
     * @param endStation    到达站
     * @param trainCarriageList    车厢列表
     * @return  余票数量
     */
    List<Integer> selectRemainingSeats(String trainId, String startStation, String endStation, List<String> trainCarriageList);


    /**
     * 查询列车有余票的车厢号集合
     *
     * @param trainId      列车 ID
     * @param seatType 车厢类型
     * @param startStation    出发站
     * @param endStation      到达站
     * @return 车厢号集合
     */
    List<String> listUsableCarriages(String trainId, Integer seatType, String startStation, String endStation);



    /**
     * 锁定选中以及沿途车票状态
     *
     * @param trainId                     列车 ID
     * @param departure                   出发站
     * @param arrival                     到达站
     * @param trainPurchaseTicketRespList 乘车人以及座位信息
     */
    void lockSeat(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketRespList);

    /**
     * 解锁选中以及沿途车票状态
     *
     * @param trainId                    列车 ID
     * @param departure                  出发站
     * @param arrival                    到达站
     * @param trainPurchaseTicketResults 乘车人以及座位信息
     */
    void unlock(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketResults);
}