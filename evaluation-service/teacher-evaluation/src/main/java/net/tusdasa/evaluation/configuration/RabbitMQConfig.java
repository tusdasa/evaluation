package net.tusdasa.evaluation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 *
 * @Author: tusdasa
 * @Date: 2020-02-22 11:26 AM
 */

@Slf4j
@EnableRabbit
@Configuration
public class RabbitMQConfig {

    /**
     * 消息转发器名
     */
    public static final String EXCHANGE_TEACHER = "evaluation_exchange_teacher";

    /**
     * 投递队列名
     */
    public static final String QUEUE_TEACHER = "teacher_evaluation";

    public static final String ROUTE_KEY_TEACHER = "evaluation.teacher";

    public RabbitMQConfig() {

    }

    /**
     * 消息队列配置
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_TEACHER, false);
    }

    /**
     * 交换机配置
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TEACHER);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTE_KEY_TEACHER);
    }

    /**
     * mq 模板配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        /**
         * 消息确认回调
         * */
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            if (b) {
                log.info("message success");
            } else {
                log.info("ID: {} message failure cause {}", correlationData, s);
            }
        });
        return rabbitTemplate;
    }
}

