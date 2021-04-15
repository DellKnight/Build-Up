package com.impl.fanout;

import com.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@RabbitListener(queues = {"fanout.wechat.queue"})
@Service
public class FanoutWechatCustomerServiceImpl implements CustomerService {
    @Override
    @RabbitHandler
    public void receiveMessage(Long message) {
        System.out.println("fanout-微信接收到的订单编号:"+message);
    }
}
