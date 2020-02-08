package net.tusdasa.evaluation.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /*
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (request.getRequestURI().equals("/service/auth/student")){
            return null;
        }else if (request.getRequestURI().equals("/service/auth/teacher")){
            return null;
        }else {
            String token = request.getHeader("Authorization");
            if (token!=null && !token.isEmpty()){
                System.out.println(token);
            }else {
                context.setSendZuulResponse(false);
                HttpServletResponse response = context.getResponse();
                try {
                    response.setContentType("application/json; charset=utf8");
                    response.getWriter().println(JSON.toJSON(new CommonResponse<String>().error("认证失败")));
                } catch (Exception e) {

                }
            }
            return null;
        }
         */
        return null;
    }
}
