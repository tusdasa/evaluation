-- 导入json
cjson = require "cjson"
-- 导入负载均衡
balancer = require "ngx.balancer"
--[[
Shared memory zones are always shared by all the Nginx worker processes in the current Nginx server instance.
-- 此cache线程安全保障
-- DICT为lua_shared_dict指令定义的共享存储区获取基于shm的Lua字典对象。
-- 共享内存区域始终由当前Nginx服务器实例中的所有Nginx工作进程共享。
--]]
cache = ngx.shared.vtimes

-- 默认配置 转发到 127.0.0.1:8081
local s1 = {}
local cluster = {}
s1["host"] = "127.0.0.1"
s1["port"] = "8081"
cluster["host"] = {}
cluster["host"][1] = s1
-- times 为-1时获取最新配置内容
cache:set("times", -1)
-- 转换为JSON 保存在缓存中
cache:set("cluster", cjson.encode(cluster))
cache:set("currentserver", 1)
