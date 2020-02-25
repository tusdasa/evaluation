package net.tusdasa.evaluation.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.Token;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    private JWTUtils jwtUtils;

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    public AccessLogFilter(JWTUtils jwtUtils, RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.jwtUtils = jwtUtils;
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
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
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token_string = request.getHeader("Authorization");
        if (token_string != null && !token_string.isEmpty()) {

            Map<String, Object> result = jwtUtils.check(token_string);

            if (result.get("code").equals(200)) {
                Token token = (Token) result.get("token");
                log.info("user:{} ==> role:{} ==> url:{}", token.getSub(), token.getRole(), request.getRequestURI());
                Student student = null;
                Teacher teacher = null;
                if (token.getRole().equals(1)) {
                    student = studentRedisTemplate.opsForValue().get(token.getSub());
                } else {
                    teacher = teacherRedisTemplate.opsForValue().get(token.getSub());
                }

                if (student != null) {
                    context.addZuulRequestHeader("studentId", student.getStudentId().toString());
                    context.addZuulRequestHeader("role", token.getRole().toString());
                } else if (teacher != null) {
                    context.addZuulRequestHeader("workId", teacher.getWorkId().toString());
                    context.addZuulRequestHeader("role", teacher.getRoleId().toString());
                } else {
                    context.setSendZuulResponse(false);
                    context.addZuulResponseHeader("Content-Type", "application/json;charset=utf8");
                    context.setResponseStatusCode(HttpStatus.OK.value());
                    context.setResponseBody((String) JSON.toJSON(new CommonResponse<String>().auth("请重新登录")).toString());
                }
            } else {
                context.setSendZuulResponse(false);
                context.addZuulResponseHeader("Content-Type", "application/json;charset=utf8");
                context.setResponseStatusCode(HttpStatus.OK.value());
                context.setResponseBody((String) JSON.toJSON(new CommonResponse<String>().auth("未授权，请登录").toString()));
            }
        } else {
            context.setSendZuulResponse(false);
            context.addZuulResponseHeader("Content-Type", "application/json;charset=utf8");
            context.setResponseStatusCode(HttpStatus.OK.value());
            context.setResponseBody(JSON.toJSON(new CommonResponse<String>().auth("未授权，请登录")).toString());
        }

        return null;
    }
}
