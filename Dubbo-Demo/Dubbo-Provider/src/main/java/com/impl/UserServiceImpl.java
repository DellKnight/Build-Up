package com.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pojo.User;
import com.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ping.dai
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @HystrixCommand
    public User getUser(int id) throws Exception {
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setSex("男");
        user.setAge(27);
        if(Math.random()>0.5){
            throw new Exception("模拟服务提供者出现异常");
        }
        return user;
    }
}
