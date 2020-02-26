package net.tusdasa.evaluation.configuration;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author: tusdasa
 * @Date: 2020-02-26 8:42 PM
 */

@Configuration
@EnableMongoRepositories(basePackages = "net.tusdasa.evaluation.dao.teacher", mongoTemplateRef = "teacherMongoTemplate")
public class TeacherMongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.teacher")
    public MongoProperties teacherMongoProperties() {
        return new MongoProperties();
    }

    @Bean
    public MongoDbFactory teacherMongoFactory(MongoProperties teacherMongoProperties) throws Exception {
        return new SimpleMongoClientDbFactory("mongodb://127.0.0.1:27017/teacherevaluation");
    }

    @Bean
    public MongoTemplate teacherMongoTemplate() throws Exception {
        return new MongoTemplate(teacherMongoFactory(teacherMongoProperties()));
    }

}
