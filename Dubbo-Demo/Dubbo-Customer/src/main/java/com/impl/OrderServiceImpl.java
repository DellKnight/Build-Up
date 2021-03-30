package com.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pojo.User;
import com.service.OrderService;
import com.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping.dai
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    @Override
    @HystrixCommand(fallbackMethod = "dealException")
    public User initOrder(int id) throws Exception {
        return userService.getUser(id);
    }

    public User dealException(int id){
        return new User("出错了","",0);
    }
}
