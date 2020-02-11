package net.tusdasa.evaluation.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    private JWTUtils jwtUtils;

    public AccessLogFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if ("/service/auth/student".equals(request.getRequestURI())) {
            return false;
        }
        if ("/service/auth/teacher".equals(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /*
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("Authorization");
        if (token != null && !token.isEmpty()) {
            try {
                jwtUtils.check(token);
            } catch (JWTVerificationException exception) {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        */
        return null;
    }
}
