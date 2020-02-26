package net.tusdasa.evaluation.configuration;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author: tusdasa
 * @Date: 2020-02-26 8:28 PM
 */
@Configuration
@EnableMongoRepositories(basePackages = "net.tusdasa.evaluation.dao.student", mongoTemplateRef = "studentMongoTemplate")
public class StudentMongoConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.student")
    public MongoProperties studentMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean
    public MongoDbFactory studentMongoFactory(MongoProperties studentMongoProperties) throws Exception {
        System.out.println("st" + studentMongoProperties.getUri());
        return new SimpleMongoClientDbFactory("mongodb://127.0.0.1:27017/evaluation");
    }

    @Primary
    @Bean
    public MongoTemplate studentMongoTemplate() throws Exception {
        return new MongoTemplate(studentMongoFactory(studentMongoProperties()));
    }
}
