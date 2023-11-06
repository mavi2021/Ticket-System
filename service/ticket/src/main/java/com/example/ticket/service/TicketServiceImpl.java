package com.example.ticket.service;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.order.dto.req.TicketOrderCreateReqDTO;
import com.example.order.dto.req.TicketOrderItemCreateReqDTO;
import com.example.order.service.OrderService;

import com.example.pay.service.PayService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.toolkit.DateUtil;
import com.example.ticket.common.enums.SourceEnum;
import com.example.ticket.common.enums.TicketStatusEnum;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.domain.SeatClassDTO;
import com.example.ticket.dto.domain.SelectSeatDTO;
import com.example.ticket.dto.domain.TicketListDTO;
import com.example.ticket.dto.remote.PayInfoRespDTO;
import com.example.ticket.dto.req.*;
import com.example.ticket.dto.resp.*;
import com.example.ticket.entity.*;
import com.example.ticket.mapper.*;
import com.example.ticket.toolkit.TimeStringComparator;
import com.example.user.dto.resp.PassengerActualRespDTO;
import com.example.user.service.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService{
    private final StationMapper stationMapper;
    private final TrainStationRelationMapper trainStationRelationMapper;
    private final TrainMapper trainMapper;
    private final TrainStationPriceMapper trainStationPriceMapper;
    private final SeatService seatService;
    private final TrainSeatSelector trainSeatSelector;
    private final TrainStationRelationService trainStationRelationService;
    private final TrainService trainService;
    private final TrainStationPriceService trainStationPriceService;
    private final ApplicationContext applicationContext;
    private TicketService ticketService;

    @PostConstruct
    void initTicketService(){
        ticketService = applicationContext.getBean(TicketService.class);
    }

    @Reference(version = "1.0.0", check = false)
    private PayService payService;

    @Reference(version = "1.0.0", check = false)
    private OrderService orderService;

    @Reference(version = "1.0.0", check = false)
    private PassengerService passengerService;


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
            Map<Integer, Integer> seatQuantityMap = seatService.loadAllSeatQuantity(String.valueOf(train.getId()),
                                                                            trainStationRelation.getDeparture(),
                                                                            trainStationRelation.getArrival());
            List<SeatClassDTO> seatClassList = trainStationPriceList.stream().map(trainStationPrice -> SeatClassDTO.builder()
                        .type(trainStationPrice.getSeatType())
                        .price(new BigDecimal(trainStationPrice.getPrice()).divide(new BigDecimal("100")))
                        .quantity(seatQuantityMap.get(trainStationPrice.getSeatType()))
                        .candidate(false)
                        .build()).collect(Collectors.toList());

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

    @Deprecated
    @Override
    public TicketPurchaseRespDTO purchaseTickets(PurchaseTicketReqDTO requestParam) {
        return ticketService.executePurchaseTickets(requestParam);
    }


    @Deprecated
    @Override
    @Transactional
    public TicketPurchaseRespDTO executePurchaseTickets(PurchaseTicketReqDTO requestParam) {
        List<TrainPurchaseTicketRespDTO> purchaseTicketRespDTOS = ticketService.obtainActualPurchaseResult(requestParam);
        seatService.lockSeat(String.valueOf(requestParam.getTrainId()), requestParam.getDeparture(), requestParam.getArrival(), purchaseTicketRespDTOS);

        TrainStationRelationRespDTO trainStationRelationRespDTO = trainStationRelationService.queryTrainStatinRelation(requestParam.getTrainId(), requestParam.getDeparture(), requestParam.getArrival());
        Train train = trainService.getById(requestParam.getTrainId());
        List<TicketOrderItemCreateReqDTO> ticketOrderItemList = BeanUtil.copyToList(purchaseTicketRespDTOS, TicketOrderItemCreateReqDTO.class);
        TicketOrderCreateReqDTO ticketOrderCreateReqDTO = TicketOrderCreateReqDTO.builder()
//                .userId()
                .username("currentUserName")
                .trainId(requestParam.getTrainId())
                .departure(requestParam.getDeparture())
                .arrival(requestParam.getArrival())
                .source(SourceEnum.INTERNET.getCode())
                .orderTime(new Date())
                .ridingDate(trainStationRelationRespDTO.getArrivalTime())
                .trainNumber(train.getTrainNumber())
                .departureTime(trainStationRelationRespDTO.getDepartureTime())
                .arrivalTime(trainStationRelationRespDTO.getArrivalTime())
                .ticketOrderItems(ticketOrderItemList)
                .build();
        String orderSn = orderService.createTicketOrder(ticketOrderCreateReqDTO);

        List<Ticket> ticketList = purchaseTicketRespDTOS.stream().map(each -> Ticket.builder()
            .trainNumber(train.getTrainNumber())
            .passengerName(each.getRealName())
            .idCard(each.getIdCard())
            .discountType(each.getDiscountType())
            .ticketPrice(1)
            .ticketStatus(TicketStatusEnum.UNPAID.getCode())
            .passengerId(each.getPassengerId())
            .seat_id(Integer.valueOf(each.getSeatId()))
            .orderSn(orderSn)
            .build()).collect(Collectors.toList());
        ticketService.saveBatch(ticketList);

        List<TicketOrderDetailRespDTO> ticketOrderDetails = BeanUtil.copyToList(purchaseTicketRespDTOS, TicketOrderDetailRespDTO.class);

        return TicketPurchaseRespDTO.builder()
                .orderSn(orderSn)
                .ticketOrderDetails(ticketOrderDetails)
                .build();
    }


    @Deprecated
    @Override
    @Transactional
    public List<TrainPurchaseTicketRespDTO> obtainActualPurchaseResult(PurchaseTicketReqDTO requestParam) {
        List<PurchaseTicketPassengerDetailDTO> passengerList = requestParam.getPassengers();
        Map<Integer, List<PurchaseTicketPassengerDetailDTO>> seatTypeMap = passengerList.stream().collect(Collectors.groupingBy(PurchaseTicketPassengerDetailDTO::getSeatType));
        List<TrainPurchaseTicketRespDTO> actualPurchaseResult = new ArrayList<>();
        seatTypeMap.forEach((seatType, passengerSeatDetails)->{
            TrainStationPriceRespDTO trainStationPriceRespDTO = trainStationPriceService.queryTrainStationPrice(String.valueOf(requestParam.getTrainId()), String.valueOf(seatType), requestParam.getDeparture(), requestParam.getArrival());
            SelectSeatDTO selectSeatDTO = SelectSeatDTO.builder()
                    .seatType(seatType)
                    .price(trainStationPriceRespDTO.getPrice())
                    .seatSelectStrategyType(requestParam.getSeatSelectStrategyType())
                    .passengerSeatDetails(passengerSeatDetails)
                    .requestParam(requestParam).build();
            List<SeatDistributeRespDTO> seatDistributeResult = trainSeatSelector.distributeSeats(selectSeatDTO);
            List<TrainPurchaseTicketRespDTO> purchaseTicketResultGroupBySeatType = BeanUtil.copyToList(seatDistributeResult, TrainPurchaseTicketRespDTO.class);
            actualPurchaseResult.addAll(purchaseTicketResultGroupBySeatType);
        });
        List<Long> passengerIds = actualPurchaseResult.stream().map(each-> Long.valueOf(each.getPassengerId())).collect(Collectors.toList());
        List<PassengerActualRespDTO> passengersDetailList = passengerService.listPassengerQueryByIds("currentUserName", passengerIds);
        Map<String, PassengerActualRespDTO> passengerActualTicketDetailMap = passengersDetailList.stream().collect(Collectors.toMap(
                PassengerActualRespDTO::getId,
                passengersDetail -> (PassengerActualRespDTO) Function.identity()
        ));
        actualPurchaseResult.forEach(each->{
            String seatId = seatService.querySeatId(requestParam.getTrainId(), requestParam.getDeparture(), requestParam.getArrival(), each.getCarriageNumber(), each.getSeatNumber());
            PassengerActualRespDTO passengerDetail = passengerActualTicketDetailMap.get(each.getPassengerId());
            BeanUtil.copyProperties(passengerDetail, each);
            each.setSeatId(seatId);
        });
        return actualPurchaseResult;
    }

    @Override
    public PayInfoRespDTO getPayInfo(String orderSn) {
        return BeanUtil.copyProperties(payService.getPayInfoByOrderSn(orderSn), PayInfoRespDTO.class);
    }

    @Deprecated
    @Override
    @Transactional
    public void cancelTicketOrder(CancelTicketOrderReqDTO requestParam) {
        com.example.order.dto.req.CancelTicketOrderReqDTO cancelTicketOrderReqDTO = BeanUtil.copyProperties(requestParam, com.example.order.dto.req.CancelTicketOrderReqDTO.class);
        boolean isSuccess = orderService.cancelTickOrder(cancelTicketOrderReqDTO);
        if(isSuccess){
            LambdaUpdateWrapper<Ticket> ticketLambdaUpdateWrapper = Wrappers.lambdaUpdate(Ticket.class)
                    .eq(Ticket::getOrderSn, requestParam.getOrderSn());
            Ticket updateTicket = Ticket.builder()
                    .ticketStatus(TicketStatusEnum.CLOSED.getCode()).build();
            ticketService.update(updateTicket, ticketLambdaUpdateWrapper);

            com.example.order.dto.resp.TicketOrderDetailRespDTO ticketOrderDetailRespDTO = orderService.queryTicketOrderByOrderSn(requestParam.getOrderSn());
            List<TrainPurchaseTicketRespDTO> purchaseTicketRespDTOS = BeanUtil.copyToList(ticketOrderDetailRespDTO.getPassengerDetails(), TrainPurchaseTicketRespDTO.class);
            seatService.unlock(String.valueOf(ticketOrderDetailRespDTO.getTrainId()), ticketOrderDetailRespDTO.getDeparture(), ticketOrderDetailRespDTO.getArrival(), purchaseTicketRespDTOS);
        }
    }


    @Override
    public void commonTicketPay(PayTicketReqDTO requestParam) {

    }

    @Override
    public void commonTicketRefund(RefundTicketReqDTO requestParam) {

    }

    @Deprecated
    @Override
    public CheckTicketRespDTO checkTicketByIdCard(CheckTicketReqDTO requestParam) {
        LambdaQueryWrapper<Ticket> ticketLambdaQueryWrapper = Wrappers.lambdaQuery(Ticket.class)
                .eq(Ticket::getIdCard, requestParam.getIdCard())
                .eq(Ticket::getTicketStatus, TicketStatusEnum.CHECKING.getCode());
        Ticket ticket = baseMapper.selectOne(ticketLambdaQueryWrapper);
        if (BeanUtil.isEmpty(ticket)){
            return CheckTicketRespDTO.builder().checkResult(false).build();
        }
        Seat seat = seatService.getById(ticket.getSeat_id());
        return CheckTicketRespDTO.builder()
                .passengerName(ticket.getPassengerName())
                .trainNumber(ticket.getTrainNumber())
                .discountType(String.valueOf(ticket.getDiscountType()))
                .departure(seat.getDeparture())
                .arrival(seat.getArrival())
                .carriageNumber(seat.getCarriageNumber())
                .seatNumber(seat.getSeatNumber())
                .checkResult(true)
                .build();
    }

}
