package net.tusdasa.evaluation.configuration;

import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTempleConfig {

    public RedisTempleConfig() {

    }

    @Bean
    public RedisTemplate<String, Student> studentRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Student> studentRedisTemplate = new RedisTemplate<>();
        studentRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return studentRedisTemplate;
    }

    @Bean
    public RedisTemplate<String, Teacher> teacherRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Teacher> teacherRedisTemplate = new RedisTemplate<>();
        teacherRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return teacherRedisTemplate;
    }

}
