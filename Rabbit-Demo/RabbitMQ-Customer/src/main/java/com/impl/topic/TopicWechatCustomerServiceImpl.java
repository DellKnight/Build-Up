package com.impl.topic;

import com.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 注解的方式绑定
 * @author ping.dai
 */
@RabbitListener(bindings = @QueueBinding(
        value=@Queue(value="topic.wechat.queue",durable = "true",autoDelete = "false"),
        exchange=@Exchange(value="topic-exchange",type = "topic"),
        key = "#.wechat.#"
))
@Service
public class TopicWechatCustomerServiceImpl implements CustomerService {
    @Override
    @RabbitHandler
    public void receiveMessage(Long message) {
        System.out.println("topic-微信接收到的订单编号:"+message);
    }
}
