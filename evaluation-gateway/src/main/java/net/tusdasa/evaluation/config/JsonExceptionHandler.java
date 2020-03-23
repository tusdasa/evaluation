package net.tusdasa.evaluation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 12:05 AM
 */

public class JsonExceptionHandler implements ErrorWebExceptionHandler {

    public JsonExceptionHandler() {
    }

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] infoByte = objectMapper.writeValueAsString(new CommonResponse<String>().error(throwable.getMessage())).getBytes();
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(infoByte);
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        serverWebExchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=utf8");
        return serverWebExchange.getResponse().writeWith(Mono.just(buffer));
    }
}
