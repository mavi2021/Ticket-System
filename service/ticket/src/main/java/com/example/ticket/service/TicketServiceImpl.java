package com.example.ticket.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.toolkit.DateUtil;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SeatClassDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.domain.TicketListDTO;
import com.example.ticket.dto.remote.PayInfoRespDTO;
import com.example.ticket.dto.req.CancelTicketOrderReqDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.req.TicketPageQueryReqDTO;
import com.example.ticket.dto.resp.TicketPageQueryRespDTO;
import com.example.ticket.dto.resp.TicketPurchaseRespDTO;
import com.example.ticket.entity.*;
import com.example.ticket.mapper.*;
import com.example.ticket.toolkit.TimeStringComparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @create 2023/9/24 8:27
 */
@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService{

    private final StationMapper stationMapper;
    private final TrainStationRelationMapper trainStationRelationMapper;
    private final TrainMapper trainMapper;
    private final TrainStationPriceMapper trainStationPriceMapper;
    private final SeatMapper seatMapper;
    private final SeatService seatService;
    private final TrainSeatSelector trainSeatSelector;



    @Override
    public TicketPageQueryRespDTO pageListTicketQuery(TicketPageQueryReqDTO requestParam) {

//       编码code与城市映射表，如：1：北京
        List<Station> stationList = stationMapper.selectList(Wrappers.emptyWrapper());
        Map<String, String> stationMap = stationList.stream()
                .collect(Collectors.toMap(Station::getCode, Station::getRegionName));

//        根据起始地code和到达地code查询所有相关列车，region表示城市
        String startStation = stationMap.get(requestParam.getFromStation());
        String endStation = stationMap.get(requestParam.getToStation());
        LambdaQueryWrapper<TrainStationRelation> stationRelationLambdaQueryWrapper
                = Wrappers.lambdaQuery(TrainStationRelation.class)
                .eq(TrainStationRelation::getStartRegion, startStation)
                .eq(TrainStationRelation::getEndRegion, endStation);
        List<TrainStationRelation> trainStationRelations
                = trainStationRelationMapper.selectList(stationRelationLambdaQueryWrapper);

//       查找所有符合条件的车次信息集合
        List<TicketListDTO> ticketListDTOS = trainStationRelations.stream().map(trainStationRelation -> {
            Train train = trainMapper.selectById(trainStationRelation.getTrainId());
            LambdaQueryWrapper<TrainStationPrice> trainStationPriceLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationPrice.class)
                    .eq(TrainStationPrice::getDeparture, trainStationRelation.getDeparture())
                    .eq(TrainStationPrice::getArrival, trainStationRelation.getArrival())
                    .eq(TrainStationPrice::getTrainId, train.getId());
            List<TrainStationPrice> trainStationPriceList =
                    trainStationPriceMapper.selectList(trainStationPriceLambdaQueryWrapper);
            Map<Integer, Integer> seatQuantityMap = seatService.loadAllSeatQuantity(train.getId(),
                                                                            trainStationRelation.getDeparture(),
                                                                            trainStationRelation.getArrival());
            List<SeatClassDTO> seatClassList = trainStationPriceList.stream().map(trainStationPrice -> {
                return SeatClassDTO.builder()
                            .type(trainStationPrice.getSeatType())
                            .price(new BigDecimal(trainStationPrice.getPrice()).divide(new BigDecimal("100")))
                            .quantity(seatQuantityMap.get(trainStationPrice.getSeatType()))
                            .candidate(false)
                            .build();
            }).collect(Collectors.toList());

            return TicketListDTO.builder()
                    .departure(trainStationRelation.getDeparture())
                    .arrival(trainStationRelation.getArrival())
                    .departureFlag(trainStationRelation.getDepartureFlag())
                    .arrivalFlag(trainStationRelation.getArrivalFlag())

                    .trainId(String.valueOf(train.getId()))
                    .trainNumber(train.getTrainNumber())
                    .trainType(train.getTrainType())
                    .trainTags(StrUtil.split(train.getTrainTag(), ","))
                    .trainBrand(train.getTrainBrand())
                    .saleTime(DateUtil.convertDateToLocalTime(train.getSaleTime(), "MM-dd HH:mm"))
                    .saleStatus(new Date().after(train.getSaleTime()) ? 0 : 1)
                    .departureTime(DateUtil.convertDateToLocalTime(train.getDepartureTime(), "HH:mm"))
                    .arrivalTime(DateUtil.convertDateToLocalTime(train.getArrivalTime(), "HH:mm"))

                    .daysArrived((int) cn.hutool.core.date.DateUtil.betweenDay(trainStationRelation.getDepartureTime(), trainStationRelation.getArrivalTime(), false))
                    .duration(DateUtil.calculateHourDifference(train.getDepartureTime(), train.getArrivalTime()))
                    .seatClassList(seatClassList)
                    .build();
        }).sorted(new TimeStringComparator()).collect(Collectors.toList());

//        按要求返回符合条件的列车车次信息
        List<Integer> trainBrandList = ticketListDTOS.stream()
                    .filter(ticketListDTO -> StrUtil.isNotBlank(ticketListDTO.getTrainBrand()))
                    .flatMap(ticketListDTO -> StrUtil.split(ticketListDTO.getTrainBrand(),",").stream())
                    .map(Integer::parseInt)
                    .distinct().sorted().collect(Collectors.toList());
        List<String> departureStationList = ticketListDTOS.stream().map(TicketListDTO::getDeparture).distinct().collect(Collectors.toList());
        List<String> arrivalStationList = ticketListDTOS.stream().map(TicketListDTO::getArrival).distinct().collect(Collectors.toList());
        List<Integer> seatClassTypeList = ticketListDTOS.stream().flatMap(ticketListDTO -> ticketListDTO.getSeatClassList().stream()).map(SeatClassDTO::getType).distinct().sorted().collect(Collectors.toList());

        return TicketPageQueryRespDTO.builder()
                .trainList(ticketListDTOS)
                .trainBrandList(trainBrandList)
                .departureStationList(departureStationList)
                .arrivalStationList(arrivalStationList)
                .seatClassTypeList(seatClassTypeList)
                .build();
    }

    @Override
    public TicketPurchaseRespDTO purchaseTicketsV1(PurchaseTicketReqDTO requestParam) {
        return null;
    }

    @Override
    public TicketPurchaseRespDTO purchaseTicketsV2(PurchaseTicketReqDTO requestParam) {
        return null;
    }

    @Override
    public TicketPurchaseRespDTO executePurchaseTickets(PurchaseTicketReqDTO requestParam) {
//        查询余票
        SelectSeatDTO.builder()
                .requestParam(requestParam)
                .build();
//        trainSeatSelector.selectSeats(re);


//        分配座席
        Integer seatType;
        List<PurchaseTicketPassengerDetailDTO> purchaseTicketPassengerDetails;
//        SelectSeatDTO selectSeatDTO = SelectSeatDTO.builder()
//                .passengerSeatDetails(purchaseTicketPassengerDetails)
//                .seatType(seatType)
//                .requestParam(requestParam)
//                .build();
//        trainSeatSelector.distributeSeats(selectSeatDTO);
//        执行购票
        return null;
    }

    @Override
    public PayInfoRespDTO getPayInfo(String orderSn) {
        

        return null;
    }

    @Override
    public void cancelTicketOrder(CancelTicketOrderReqDTO requestParam) {

    }

}
