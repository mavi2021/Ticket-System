package com.example.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.common.enums.SeatStatusEnum;
import com.example.ticket.common.enums.VehicleSeatRelationEnum;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.entity.Route;
import com.example.ticket.entity.Seat;
import com.example.ticket.entity.Train;
import com.example.ticket.mapper.SeatMapper;
import com.example.ticket.mapper.TrainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @create 2023/9/25 21:42
 */
@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService{

    private final TrainMapper trainMapper;
    private final TrainStationService trainStationService;

    @Override
    public Map<Integer, Integer> loadAllSeatQuantity(Long trainId, String departure, String arrival) {
        Train train = trainMapper.selectById(trainId);
        List<Integer> seatTypes = VehicleSeatRelationEnum.getSeatTypesByType(train.getTrainType());
        return seatTypes.stream().collect(Collectors.toMap(
                type -> type,
                type -> loadSeatQuantityBySeatType(train.getId(), type, departure, arrival)));
    }

    @Override
    public Integer loadSeatQuantityBySeatType(Long trainId, Integer seatType, String departure, String arrival) {
        LambdaQueryWrapper<Seat> queryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getStartStation, departure)
                .eq(Seat::getEndStation, arrival)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode());
        return Math.toIntExact(baseMapper.selectCount(queryWrapper));
    }

    @Override
    public List<String> listAvailableSeat(String trainId, String carriageNumber, Integer seatType, String departure, String arrival) {
        LambdaQueryWrapper<Seat> seatLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getCarriageNumber, carriageNumber)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getStartStation, departure)
                .eq(Seat::getEndStation, arrival)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode());
//                .select(Seat::getSeatNumber);
        List<Seat> seatsList = baseMapper.selectList(seatLambdaQueryWrapper);
        return seatsList.stream().map(Seat::getSeatNumber).collect(Collectors.toList());
    }

    @Override
    public List<String> listUsableCarriages(Long trainId, Integer seatType, String startStation, String endStation) {
        LambdaQueryWrapper<Seat> carriageLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getStartStation, startStation)
                .eq(Seat::getEndStation, endStation)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode())
                .select(Seat::getCarriageNumber);
        List<Seat> carriageLists = baseMapper.selectList(carriageLambdaQueryWrapper);
        return carriageLists.stream().map(Seat::getCarriageNumber).distinct().collect(Collectors.toList());
    }

    @Override
    public  List<Integer> selectRemainingSeats(Long trainId, String startStation, String endStation, List<String> trainCarriageList) {
        LambdaQueryWrapper<Seat> remainingSeatsCountWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getStartStation, startStation)
                .eq(Seat::getEndStation, endStation)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode())
                .in(Seat::getCarriageNumber, trainCarriageList)
                .groupBy(Seat::getCarriageNumber)
                .select(Seat::getCarriageNumber, Seat::getCarriageGroupCount);
        List<Seat> remainingSeats = baseMapper.selectList(remainingSeatsCountWrapper);
        System.out.println(remainingSeats);
        return remainingSeats.stream().map(Seat::getCarriageGroupCount).collect(Collectors.toList());
    }

    @Override
    public void lockSeat(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketRespList) {
        List<Route> routeList = trainStationService.listTakeoutTrainStationRoute(trainId, departure, arrival);
        trainPurchaseTicketRespList.forEach(each->{
            routeList.forEach(item ->{
                LambdaUpdateWrapper<Seat> seatLambdaUpdateWrapper = Wrappers.lambdaUpdate(Seat.class)
                        .eq(Seat::getTrainId, trainId)
                        .eq(Seat::getStartStation, item.getStartStation())
                        .eq(Seat::getEndStation, item.getEndStation())
                        .eq(Seat::getCarriageNumber, each.getCarriageNumber())
                        .eq(Seat::getSeatNumber, each.getSeatNumber());
                Seat updateSeat = Seat.builder()
                        .seatStatus(SeatStatusEnum.LOCKED.getCode())
                        .build();
                baseMapper.update(updateSeat, seatLambdaUpdateWrapper);
            });
        });
    }

    @Override
    public void unlock(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketResults) {
        List<Route> routeList = trainStationService.listTakeoutTrainStationRoute(trainId, departure, arrival);
        trainPurchaseTicketResults.forEach(each->{
            routeList.forEach(item ->{
                LambdaUpdateWrapper<Seat> seatLambdaUpdateWrapper = Wrappers.lambdaUpdate(Seat.class)
                        .eq(Seat::getTrainId, trainId)
                        .eq(Seat::getStartStation, item.getStartStation())
                        .eq(Seat::getEndStation, item.getEndStation())
                        .eq(Seat::getCarriageNumber, each.getCarriageNumber())
                        .eq(Seat::getSeatNumber, each.getSeatNumber());
                Seat updateSeat = Seat.builder()
                        .seatStatus(SeatStatusEnum.AVAILABLE.getCode())
                        .build();
                baseMapper.update(updateSeat, seatLambdaUpdateWrapper);
            });
        });
    }

}
