package net.tusdasa.evaluation.config;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Author: tusdasa
 * @Date: 2020-03-23 1:40 PM
 */

@Configuration
public class GatewayConfig {


    public GatewayConfig() {
    }

    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new JsonExceptionHandler();
    }
}
