package net.tusdasa.evaluation.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.tusdasa.evaluation.authority.Authority;
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

    private String[] msg = new String[]
            {
                    "Please log in the server with your account and password",
                    "Token is empty",
                    "Token verify failure"
            };

    private JWTUtils jwtUtils;

    public AuthGlobalFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 默认返回的消息msg[0]
        int msgIndex = 0;
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

                        if (!token.getRole().equals(Authority.STUDENT)) {
                            // 是老师 设置工号
                            exchange.getRequest().mutate().header("workId", token.getSub()).build();
                        } else {
                            // 是学生 设置学号
                            exchange.getRequest().mutate().header("studentId", token.getSub()).build();
                        }
                        exchange.getRequest().mutate().header("role", String.valueOf(token.getRole())).build();
                        LOG.info("userId =>{} : roleId => {} : uri => {}", token.getSub(), token.getRole(), request.getPath().value());
                        flag = true;
                    } else {
                        LOG.info("token check failure {}", tokenString);
                        msgIndex = 2;
                    }
                } else {
                    msgIndex = 1;
                }
            } else {
                msgIndex = 1;
            }
        }
        if (flag) {
            return chain.filter(exchange);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            CommonResponse<String> response = new CommonResponse<String>().auth(msg[msgIndex]);
            try {
                byte[] infoByte = objectMapper.writeValueAsString(response).getBytes();
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(infoByte);
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=utf8");
                return exchange.getResponse().writeWith(Mono.just(buffer));
            } catch (JsonProcessingException e) {
                return exchange.getResponse().setComplete();
            }
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
