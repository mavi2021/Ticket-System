package com.example.ticket.tokenbucket;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.ticket.dto.domain.PurchaseTicketPassengerDetailDTO;
import com.example.ticket.dto.req.PurchaseTicketReqDTO;
import com.example.ticket.dto.resp.SeatTypeCountRespDTO;
import com.example.ticket.entity.Route;
import com.example.ticket.entity.Train;
import com.example.ticket.service.SeatService;
import com.example.ticket.service.TrainService;
import com.example.ticket.service.TrainStationService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.ticket.common.constant.RedisKeyConstant.LUA_TICKET_AVAILABILITY_TOKEN_BUCKET_PATH;
import static com.example.ticket.common.constant.RedisKeyConstant.TICKET_AVAILABILITY_TOKEN_BUCKET;

@Component
public class TicketAvailabilityTokenBucket implements AbstractTokenBucket<PurchaseTicketReqDTO>{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private TrainStationService trainStationService;

    @Resource
    private SeatService seatService;

    @Resource
    private TrainService trainService;

    @Resource
    private RedisTemplate redisTemplate;
//    @Deprecated
    @Override
    public boolean takeTokenFromBucket(PurchaseTicketReqDTO requestParam) {
        String key = TICKET_AVAILABILITY_TOKEN_BUCKET + requestParam.getTrainId();
        Boolean isBucketNotEmpty = stringRedisTemplate.hasKey(key);
        Map<String, String> ticketAvailabilityTokenMap = new HashMap<>();
        if (Boolean.FALSE.equals(isBucketNotEmpty)){
            Train train = trainService.getById(requestParam.getTrainId());
            List<SeatTypeCountRespDTO> seatTypeCountRespDTOS = seatService.listSeatTypeCount(requestParam.getTrainId(), requestParam.getDeparture(), requestParam.getArrival());
            seatTypeCountRespDTOS.forEach(each->{
                String hashInnerKey = requestParam.getDeparture() + "_" + requestParam.getArrival() + "_" + each.getSeatType();
                ticketAvailabilityTokenMap.put(hashInnerKey, each.getSeatCount().toString());
            });
            stringRedisTemplate.opsForHash().putAll(key, ticketAvailabilityTokenMap);
//            long keyValidTime = train.getDepartureTime().getTime() - new Date().getTime();
//            stringRedisTemplate.expire(key, keyValidTime, TimeUnit.MILLISECONDS);
        }

        DefaultRedisScript<Long> longDefaultRedisScript = new DefaultRedisScript<>();
        longDefaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(LUA_TICKET_AVAILABILITY_TOKEN_BUCKET_PATH)));

        Map<Integer, Long> seatTypeMap = requestParam.getPassengers().stream()
                .collect(Collectors.groupingBy(PurchaseTicketPassengerDetailDTO::getSeatType, Collectors.counting()));
        JSONArray jsonArray = seatTypeMap.entrySet().stream().map(entry -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("seatType", entry.getKey());
            jsonObject.set("purchaseTicketNum", entry.getValue());
            return jsonObject;
        }).collect(Collectors.toCollection(JSONArray::new));

        List<Route> routeList = trainStationService.listTakeoutTrainStationRoute(requestParam.getTrainId(), requestParam.getDeparture(), requestParam.getArrival());

        Object execute = redisTemplate.execute(longDefaultRedisScript, ListUtil.of(key, requestParam.getDeparture(), requestParam.getArrival()), JSONUtil.toJsonStr(jsonArray), JSONUtil.toJsonStr(routeList));
        System.out.println(execute);
        return false;
//        return Objects.equals(result, 1L);

    }
}
