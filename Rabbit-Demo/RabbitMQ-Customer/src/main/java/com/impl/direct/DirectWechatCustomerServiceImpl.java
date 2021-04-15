package com.impl.direct;

import com.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@RabbitListener(queues = {"direct.wechat.queue"})
@Service
public class DirectWechatCustomerServiceImpl implements CustomerService {
    @Override
    @RabbitHandler
    public void receiveMessage(Long message) {
        System.out.println("direct-微信接收到的订单编号:"+message);
    }
}
