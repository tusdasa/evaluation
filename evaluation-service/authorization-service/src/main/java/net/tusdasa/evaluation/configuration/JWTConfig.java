package net.tusdasa.evaluation.configuration;

import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JWTConfig {

    @Value("${auth.enable}")
    private Boolean enable = false;

    @Value("${auth.secret}")
    private String secret = null;

    public JWTConfig() {
    }

    @Bean
    @Scope("singleton")
    public JWTUtils jwtUtils() {
        return new JWTUtils(this.enable, secret);
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
