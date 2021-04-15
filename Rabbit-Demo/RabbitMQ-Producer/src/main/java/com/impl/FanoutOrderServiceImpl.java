package com.impl;

import com.service.OrderService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@Service
public class FanoutOrderServiceImpl implements OrderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void takeOrder(Long userId, Long productId, int sum) {
        //模拟下订单
        System.out.println("用户编号:"+userId+"订单编号:"+productId+"数量:"+sum);
        //发送消息
        rabbitTemplate.convertAndSend("fanout-exchange","",productId);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {

            }
        });
    }

}
