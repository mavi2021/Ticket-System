package com.example.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticket.common.enums.SeatStatusEnum;
import com.example.ticket.common.enums.VehicleSeatRelationEnum;
import com.example.ticket.dto.req.TrainPurchaseTicketRespDTO;
import com.example.ticket.dto.resp.SeatTypeCountRespDTO;
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

@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Service(version = "1.0.0",protocol = "dubbo")
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService{

    private final TrainMapper trainMapper;
    private final TrainStationService trainStationService;

    @Deprecated
    @Override
    public String querySeatId(String trainId, String departure, String arrival, String carriageNum, String seatNumber) {
        LambdaQueryWrapper<Seat> seatLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getDeparture, departure)
                .eq(Seat::getArrival, arrival)
                .eq(Seat::getCarriageNumber, carriageNum)
                .eq(Seat::getSeatNumber, seatNumber);
        return String.valueOf(baseMapper.selectOne(seatLambdaQueryWrapper).getId());
    }

    @Override
    public Map<Integer, Integer> loadAllSeatQuantity(String trainId, String departure, String arrival) {
        Train train = trainMapper.selectById(trainId);
        List<Integer> seatTypes = VehicleSeatRelationEnum.getSeatTypesByType(train.getTrainType());
        return seatTypes.stream().collect(Collectors.toMap(
                type -> type,
                type -> loadSeatQuantityBySeatType(String.valueOf(train.getId()), type, departure, arrival)));
    }

    @Override
    public Integer loadSeatQuantityBySeatType(String  trainId, Integer seatType, String departure, String arrival) {
        LambdaQueryWrapper<Seat> queryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getDeparture, departure)
                .eq(Seat::getArrival, arrival)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode());
        return Math.toIntExact(baseMapper.selectCount(queryWrapper));
    }

    @Override
    public List<String> listAvailableSeat(String trainId, String carriageNumber, Integer seatType, String departure, String arrival) {
        LambdaQueryWrapper<Seat> seatLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getCarriageNumber, carriageNumber)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getDeparture, departure)
                .eq(Seat::getArrival, arrival)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode());
//                .select(Seat::getSeatNumber);
        List<Seat> seatsList = baseMapper.selectList(seatLambdaQueryWrapper);
        return seatsList.stream().map(Seat::getSeatNumber).collect(Collectors.toList());
    }

    @Override
    public List<String> listUsableCarriages(String trainId, Integer seatType, String startStation, String endStation) {
        LambdaQueryWrapper<Seat> carriageLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getSeatType, seatType)
                .eq(Seat::getDeparture, startStation)
                .eq(Seat::getArrival, endStation)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode())
                .select(Seat::getCarriageNumber);
        List<Seat> carriageLists = baseMapper.selectList(carriageLambdaQueryWrapper);
        return carriageLists.stream().map(Seat::getCarriageNumber).distinct().collect(Collectors.toList());
    }

    @Override
    public List<SeatTypeCountRespDTO> listSeatTypeCount(String trainId, String departure, String arrival) {
        LambdaQueryWrapper<Seat> seatLambdaQueryWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getDeparture, departure)
                .eq(Seat::getArrival, arrival)
                .groupBy(Seat::getSeatType)
                .select(Seat::getSeatType, Seat::getGroupCount);
        List<Map<String, Object>> seatCountMap = baseMapper.selectMaps(seatLambdaQueryWrapper);
        return seatCountMap.stream().map(each->{
            Integer seatType = (Integer) each.get("seat_type");
            Integer groupCount = Integer.valueOf(each.get("groupCount").toString()) ;
            return SeatTypeCountRespDTO.builder()
                    .seatType(seatType)
                    .seatCount(groupCount).build();
        }).collect(Collectors.toList());
    }

    @Override
    public  List<Integer> selectRemainingSeats(String trainId, String startStation, String endStation, List<String> trainCarriageList) {
        LambdaQueryWrapper<Seat> remainingSeatsCountWrapper = Wrappers.lambdaQuery(Seat.class)
                .eq(Seat::getTrainId, trainId)
                .eq(Seat::getDeparture, startStation)
                .eq(Seat::getArrival, endStation)
                .eq(Seat::getSeatStatus, SeatStatusEnum.AVAILABLE.getCode())
                .in(Seat::getCarriageNumber, trainCarriageList)
                .groupBy(Seat::getCarriageNumber)
                .select(Seat::getCarriageNumber, Seat::getGroupCount);
        List<Seat> remainingSeats = baseMapper.selectList(remainingSeatsCountWrapper);
        return remainingSeats.stream().map(Seat::getGroupCount).collect(Collectors.toList());
    }

    @Override
    public void lockSeat(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketRespList) {
        List<Route> routeList = trainStationService.listTakeoutTrainStationRoute(trainId, departure, arrival);
        trainPurchaseTicketRespList.forEach(each->{
            routeList.forEach(item ->{
                LambdaUpdateWrapper<Seat> seatLambdaUpdateWrapper = Wrappers.lambdaUpdate(Seat.class)
                        .eq(Seat::getTrainId, trainId)
                        .eq(Seat::getDeparture, item.getStartStation())
                        .eq(Seat::getArrival, item.getEndStation())
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
        trainPurchaseTicketResults.forEach(each-> routeList.forEach(item ->{
            LambdaUpdateWrapper<Seat> seatLambdaUpdateWrapper = Wrappers.lambdaUpdate(Seat.class)
                    .eq(Seat::getTrainId, trainId)
                    .eq(Seat::getDeparture, item.getStartStation())
                    .eq(Seat::getArrival, item.getEndStation())
                    .eq(Seat::getCarriageNumber, each.getCarriageNumber())
                    .eq(Seat::getSeatNumber, each.getSeatNumber());
            Seat updateSeat = Seat.builder()
                    .seatStatus(SeatStatusEnum.AVAILABLE.getCode())
                    .build();
            baseMapper.update(updateSeat, seatLambdaUpdateWrapper);
        }));
    }

}
