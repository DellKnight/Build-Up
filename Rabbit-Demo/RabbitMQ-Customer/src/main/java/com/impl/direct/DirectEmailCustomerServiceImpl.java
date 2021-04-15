package com.impl.direct;

import com.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@RabbitListener(queues = {"direct.email.queue"})
@Service
public class DirectEmailCustomerServiceImpl implements CustomerService {
    @Override
    @RabbitHandler
    public void receiveMessage(Long message) {
        System.out.println("direct-电子邮件接收到的订单编号:"+message);
    }
}
