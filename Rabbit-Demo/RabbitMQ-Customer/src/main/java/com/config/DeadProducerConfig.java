package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 死信交换机配置
 * @author ping.dai
 */
@Configuration
public class DeadProducerConfig {
    /**
     * 声明direct类型的交换机
     * @return
     */
    @Bean
    public DirectExchange directDeadExchange(){
        return new DirectExchange("dead-exchange",true,false);
    }
    /**
     * 声明死信队列
     * @return
     */
    @Bean
    public Queue directDeadQueue(){
        return new Queue("direct.dead.queue",true,false,false);
    }
    /**
     * 绑定死信队列
     * @return
     */
    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(directDeadQueue()).to(directDeadExchange()).with("dead-key");
    }
}
