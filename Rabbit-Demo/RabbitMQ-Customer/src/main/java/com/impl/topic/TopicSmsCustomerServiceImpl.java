package com.impl.topic;

import com.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 注解的方式绑定
 * @author ping.dai
 */
@RabbitListener(bindings = @QueueBinding(
        value=@Queue(value="topic.sms.queue",durable = "true",autoDelete = "false"),
        exchange=@Exchange(value="topic-exchange",type = "topic"),
        key = "#.sms.#"
))
@Service
public class TopicSmsCustomerServiceImpl implements CustomerService {
    @Override
    @RabbitHandler
    public void receiveMessage(Long message) {
        System.out.println("topic-短信接收到的订单编号:"+message);
    }
}
