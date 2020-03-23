package net.tusdasa.evaluation.configuration;

import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTempleConfig {

    @Bean
    public RedisTemplate<String, Student> studentRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Student> studentRedisTemplate = new RedisTemplate<>();
        studentRedisTemplate.setConnectionFactory(redisConnectionFactory);
        studentRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Student.class));
        studentRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Student.class));
        studentRedisTemplate.setKeySerializer(new StringRedisSerializer());
        studentRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return studentRedisTemplate;
    }

    @Bean
    public RedisTemplate<String, Teacher> teacherRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Teacher> teacherRedisTemplate = new RedisTemplate<>();
        teacherRedisTemplate.setConnectionFactory(redisConnectionFactory);
        teacherRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Teacher.class));
        teacherRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Teacher.class));
        teacherRedisTemplate.setKeySerializer(new StringRedisSerializer());
        teacherRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return teacherRedisTemplate;
    }
}
