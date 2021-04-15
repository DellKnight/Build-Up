package com.impl;

import com.service.OrderService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@Service
public class DirectOrderServiceImpl implements OrderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void takeOrder(Long userId, Long productId, int sum) {
        //模拟下订单
        System.out.println("用户编号:"+userId+"订单编号:"+productId+"数量:"+sum);
        //发送消息,给消息单独设置过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("direct-exchange","sms-key",productId);
        rabbitTemplate.convertAndSend("direct-exchange","email-key",productId,messagePostProcessor);
        rabbitTemplate.convertAndSend("direct-exchange","wechat-key",productId,messagePostProcessor);
    }
}
