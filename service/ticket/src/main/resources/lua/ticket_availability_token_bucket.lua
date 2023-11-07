local seatCountMapJsonStr = ARGV[1]
local routeListJsonStr = ARGV[2]
local seatCountMap = cjson.decode(seatCountMapJsonStr)
local routeList = cjson.decode(routeListJsonStr)
local key = KEYS[1]
local departure = KEYS[2]
local arrival = KEYS[3]

for seatCountIndex, seatCount in ipairs(seatCountMap) do
    local seatType = tonumber(seatCount.seatType)
    local count = tonumber(seatCount.count)
    local hashInnerKey = departure .. arrival .. seatType
    local ticketAvailabilityTokenBucketValue = tonumber(redis.call('hget', key, hashInnerKey))
    --if ticketAvailabilityTokenBucketValue

end

--for index, jsonObj in ipairs(seatCountMap) do
--    local seatType = tonumber(jsonObj.seatType)
--    local count = tonumber(jsonObj.count)
--    for routeIndex, route in ipairs(routeList) do
--        local midDeparture = tostring(route.startStation)
--        local midArrival = tostring(route.endStation)
--        local hashInnerKey = midDeparture .. midArrival .. seatType
--        --local ticketAvailabilityTokenBucketValue = tonumber(redis.call('hget', key, tostring(hashInnerKey)))
--        --if ticketAvailabilityTokenBucketValue >= 0 then
--        redis.call('hincrby', key, tostring(hashInnerKey), count)
--        --end
--    end
--end