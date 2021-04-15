package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout类型exchange配置类的方式绑定(推荐)
 * @author ping.dai
 */
@Configuration
public class FanoutProducerConfig {
    /**
     * 声明fanout类型的交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange",true,false);
    }
    /**
     * 声明sms队列
     * @return
     */
    @Bean
    public Queue fanoutSmsQueue(){
        return new Queue("fanout.sms.queue",true,false,false);
    }
    /**
     * 声明email队列
     * @return
     */
    @Bean
    public Queue fanoutEmailQueue(){
        return new Queue("fanout.email.queue",true,false,false);
    }
    /**
     * 声明wechat队列
     * @return
     */
    @Bean
    public Queue fanoutWechatQueue(){
        return new Queue("fanout.wechat.queue",true,false,false);
    }
    /**
     * 绑定sms队列
     * @return
     */
    @Bean
    public Binding smsBinding1(){
        return BindingBuilder.bind(fanoutSmsQueue()).to(fanoutExchange());
    }
    /**
     * 绑定email队列
     * @return
     */
    @Bean
    public Binding emailBinding1(){
        return BindingBuilder.bind(fanoutEmailQueue()).to(fanoutExchange());
    }
    /**
     * 绑定wechat队列
     * @return
     */
    @Bean
    public Binding wechatBinding1(){
        return BindingBuilder.bind(fanoutWechatQueue()).to(fanoutExchange());
    }
}
