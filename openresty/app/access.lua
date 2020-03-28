redis = require "redis"
local cache = ngx.shared.vtimes
local vtimes = cache:get("times")
-- �ж��Ƿ�Ҫ��ȡ��������
if (vtimes > 0) then
    vtimes = vtimes - 1
    cache:replace("times", vtimes)
    return
else
    -- �滻��ǰ����
    cache:replace("times", 10)
    -- ����redis
    local redisclient = redis:new()
    redisclient:set_timeouts(1000, 1000, 1000)
    local ok, err = redisclient:connect("127.0.0.1", 6379)
    -- ���ӳɹ�
    if ok then
        -- ��ȡ����
        local cluster = redisclient:get("cluster")
        -- ��������
        if not (cluster == ngx.null) then
            cache:set("cluster", cluster)
        end
    end
    return
end
