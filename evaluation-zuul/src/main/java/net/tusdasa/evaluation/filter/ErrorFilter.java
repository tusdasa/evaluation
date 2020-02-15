package net.tusdasa.evaluation.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.setSendZuulResponse(false);
        log.error("error info {}", context.getThrowable().getMessage());
        context.addZuulResponseHeader("Content-Type", "application/json;charset=utf8");
        context.setResponseStatusCode(HttpStatus.OK.value());
        context.setResponseBody(JSON.toJSON(new CommonResponse<String>().error("出现错误请重试")).toString());
        return null;
    }


}
