package net.tusdasa.evaluation.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tusdasa
 * @Date: 2020-03-19 5:19 PM
 */

@Component
public class RoleInterceptor implements HandlerInterceptor {

    public RoleInterceptor() {
    }

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        String roleId = request.getHeader("role");

        if (roleId != null && !roleId.equals("")) {
            if (Integer.valueOf(roleId).equals(Authority.ADMIN)) {
                return true;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                response.setHeader("Content-Type", "application/json;charset=utf8");
                response.getWriter().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new CommonResponse<String>().bad()));
                return false;
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setHeader("Content-Type", "application/json;charset=utf8");
            response.getWriter().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new CommonResponse<String>().bad()));
            return false;
        }
         */
        return true;
    }
}
