package net.tusdasa.evaluation.configuration;

import net.tusdasa.evaluation.mq.ResultReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
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
     * */
    public static final String topicExchangeName = "evaluation_exchange";

    /**
     * 投递队列名
     * */
    public static final String queueName = "evaluation";

    public RabbitMQConfig() {

    }


    /**
     * 消息队列配置
     * */
    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * 交换机配置
     * */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * 绑定队列和交换机
     * */
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("evaluation.#");
    }

    /**
     * 消息接收回调类
     * */
    @Bean
    public MessageListenerAdapter listenerAdapter(ResultReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    /**
     * 消息适配
     * */
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

}
