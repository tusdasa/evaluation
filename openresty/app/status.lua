local template = require "resty.template"
-- local template = require "resty.template.safe"

ngx.header.content_type = "text/html; charset=utf-8"
cache = ngx.shared.vtimes
local times = cache:get("times")

--local info = {}
--info["times"] = times
--info["server"] = {}
--info["server"] = cjson.decode(cache:get("cluster"))
local hosts = cjson.decode(cache:get("cluster"))

template.render("view.html", { times = times, serverList = hosts["host"] })