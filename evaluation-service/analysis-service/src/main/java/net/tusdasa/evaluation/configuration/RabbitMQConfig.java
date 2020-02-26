package net.tusdasa.evaluation.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类接收端
 *
 * @Author: tusdasa
 * @date: 2020-02-22 11:28 AM
 */

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    /**
     * 消息转发器名
     */
    public static final String EXCHANGE_TEACHER = "evaluation_exchange_teacher";
    public static final String EXCHANGE_STUDENT = "evaluation_exchange_student";

    /**
     * 投递队列名
     */
    public static final String QUEUE_STUDENT = "student_evaluation";

    public static final String QUEUE_TEACHER = "teacher_evaluation";

    public static final String ROUTE_KEY_STUDENT = "evaluation.student";

    public static final String ROUTE_KEY_TEACHER = "evaluation.teacher";

    public RabbitMQConfig() {

    }
}
