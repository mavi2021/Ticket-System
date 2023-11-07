local seatCountMapJsonStr = ARGV[1]
local routeListJsonStr = ARGV[2]
local seatCountMap = cjson.decode(seatCountMapJsonStr)
local routeList = cjson.decode(routeListJsonStr)
local key = KEYS[1]

for seatCountIndex, seatCount in ipairs(seatCountMap) do
    for routeIndex, route in ipairs(routeList) do

    end
end