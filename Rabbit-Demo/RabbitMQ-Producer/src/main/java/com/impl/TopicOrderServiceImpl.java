package com.impl;

import com.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@Service
public class TopicOrderServiceImpl implements OrderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void takeOrder(Long userId, Long productId, int sum) {
        //模拟下订单
        System.out.println("用户编号:"+userId+"订单编号:"+productId+"数量:"+sum);
        //发送消息
        rabbitTemplate.convertAndSend("topic-exchange","com.sms.example",productId);
        rabbitTemplate.convertAndSend("topic-exchange","com.email.example",productId);
        rabbitTemplate.convertAndSend("topic-exchange","com.wechat.example",productId);
    }
}
