package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * direct类型exchange配置类的方式绑定(推荐)
 * @author ping.dai
 */
@Configuration
public class DirectProducerConfig {
    /**
     * 声明direct类型的交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct-exchange",true,false);
    }
    /**
     * 声明sms队列
     * @return
     */
    @Bean
    public Queue directSmsQueue(){
        //设置队列中的消息过期时间5秒
        Map<String,Object> args = new HashMap<String,Object>(2);
        args.put("x-message-ttl",5000);
        //绑定死信交换机
        args.put("x-dead-letter-exchange","dead-exchange");
        //fanout不需要配置key
        args.put("x-dead-letter-routing-key","dead-key");
        return new Queue("direct.sms.queue",true,false,false,args);
    }
    /**
     * 声明email队列
     * @return
     */
    @Bean
    public Queue directEmailQueue(){
        return new Queue("direct.email.queue",true,false,false);
    }
    /**
     * 声明wechat队列
     * @return
     */
    @Bean
    public Queue directWechatQueue(){
        return new Queue("direct.wechat.queue",true,false,false);
    }
    /**
     * 绑定sms队列
     * @return
     */
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(directSmsQueue()).to(directExchange()).with("sms-key");
    }
    /**
     * 绑定email队列
     * @return
     */
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(directEmailQueue()).to(directExchange()).with("email-key");
    }
    /**
     * 绑定wechat队列
     * @return
     */
    @Bean
    public Binding wechatBinding(){
        return BindingBuilder.bind(directWechatQueue()).to(directExchange()).with("wechat-key");
    }
}
