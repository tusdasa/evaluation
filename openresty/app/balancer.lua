local cache = ngx.shared.vtimes;
-- 获取当前服务器
local hosts = cjson.decode(cache:get("cluster"));
-- 获取当前服务器下标
local currentserver = cache:get("currentserver")
-- 获取hosts大小
local size = table.getn(hosts["host"])
-- 获取当前服务器列表
local serverList = hosts["host"]

-- 只有一台服务器
if (size == 1) then
    -- 配置当前转发地址
    balancer.set_current_peer(serverList[1]["host"], serverList[1]["port"])
else
    -- 多台开始轮询
    if (size < currentserver) then
        -- 超出当前服务列表 变为第一个
        currentserver = 1
    end
    balancer.set_current_peer(serverList[currentserver]["host"], serverList[currentserver]["port"])
    -- 下一台服务器
    cache:set("currentserver", currentserver + 1)
end
