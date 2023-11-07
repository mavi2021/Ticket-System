local seatStatisticMapJsonStr = ARGV[1]
local routeListJsonStr = ARGV[2]
local seatStatisticMap = cjson.decode(seatStatisticMapJsonStr)
local routeList = cjson.decode(routeListJsonStr)
local key = KEYS[1]
local departure = KEYS[2]
local arrival = KEYS[3]

for index, seatStatistic in ipairs(seatStatisticMap) do
    local seatType = tonumber(seatStatistic.seatType)
    local purchaseTicketNum = tonumber(seatStatistic.purchaseTicketNum)
    local hashInnerKey = departure .. "_" .. arrival .. "_" ..seatType
    local ticketAvailabilityTokenBucketValue = tonumber(redis.call('hget', key, hashInnerKey))
    if ticketAvailabilityTokenBucketValue < purchaseTicketNum then
        return 0
    end
end

for index, seatStatistic in ipairs(seatStatisticMap) do
    local seatType = tonumber(seatStatistic.seatType)
    local purchaseTicketNum = tonumber(seatStatistic.purchaseTicketNum)
    for routeIndex, route in ipairs(routeList) do
        local midDeparture = tostring(route.startStation)
        local midArrival = tostring(route.endStation)
        local hashInnerKey = midDeparture .. "_" .. midArrival .. "_" .. seatType
        redis.call('hincrby', key, tostring(hashInnerKey), -purchaseTicketNum)
    end
end

return 1