package net.tusdasa.evaluation.configuration;

import net.tusdasa.evaluation.entity.ThirdKpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
public class EhCacheConfig {

    public EhCacheConfig() {
    }
    @Bean
    public RedisTemplate<String, List<ThirdKpi>> thirdKpiRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, List<ThirdKpi>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
