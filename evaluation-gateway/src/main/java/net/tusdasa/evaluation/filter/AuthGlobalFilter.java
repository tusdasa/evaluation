package net.tusdasa.evaluation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.Token;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Author: tusdasa
 * @Date: 2020-03-23 3:50 PM
 */

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(AuthGlobalFilter.class);

    private JWTUtils jwtUtils;

    public AuthGlobalFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 放行与否 默认不放行
        boolean flag = false;
        // 两个不拦截的URI
        String uri1 = "/auth/student";
        String uri2 = "/auth/teacher";
        // 获得当前的url
        ServerHttpRequest request = exchange.getRequest();
        if (request.getPath().value().equals(uri1) || request.getPath().value().equals(uri2)) {
            // 需要放行
            flag = true;
        } else {
            // 需要拦截检查
            // 有Authorization头
            if (request.getHeaders().containsKey("Authorization")) {
                // 取Authorization头字符串
                String tokenString = request.getHeaders().get("Authorization").get(0);
                // 是否为空
                if (tokenString != null && !tokenString.isEmpty()) {
                    // 校验
                    Map<String, Object> checkResult = jwtUtils.check(tokenString);
                    // 是否校验成功
                    if (checkResult.get("code").equals(200)) {
                        // 校验成功
                        Token token = (Token) checkResult.get("token");

                        if (!token.getRole().equals(1)) {
                            exchange.getRequest().mutate().header("workId", token.getSub()).build();
                            // 是老师 设置工号
                        } else {
                            // 是学生 设置学号
                            exchange.getRequest().mutate().header("studentId", token.getSub()).build();
                        }
                        exchange.getRequest().mutate().header("role", String.valueOf(token.getRole())).build();
                        LOG.info("userId =>{} : roleId => {} : uri => {}", token.getSub(), token.getRole(), request.getPath().value());
                        flag = true;
                    } else {
                        LOG.info("token check failure {}", tokenString);
                    }
                }
            }
        }

        if (flag) {
            return chain.filter(exchange);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] infoByte = objectMapper.writeValueAsString(new CommonResponse<String>().auth("请进行身份认证")).getBytes();
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(infoByte);
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=utf8");
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
