redis = require "redis"
local cache = ngx.shared.vtimes
local vtimes = cache:get("times")
-- 判断是否要获取最新配置
if (vtimes > 0) then
    vtimes = vtimes - 1
    cache:replace("times", vtimes)
    return
else
    -- 替换当前次数
    cache:replace("times", 10)
    -- 连接redis
    local redisclient = redis:new()
    redisclient:set_timeouts(1000, 1000, 1000)
    local ok, err = redisclient:connect("127.0.0.1", 6379)
    -- 连接成功
    if ok then
        -- 获取配置
        local cluster = redisclient:get("cluster")
        -- 更新配置
        if not (cluster == ngx.null) then
            cache:set("cluster", cluster)
        end
    end
    return
end
