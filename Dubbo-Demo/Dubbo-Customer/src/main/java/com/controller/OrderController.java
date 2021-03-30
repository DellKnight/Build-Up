package com.controller;

import com.pojo.User;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ping.dai
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/initOrder/{id}")
    public User initOrder(@PathVariable int id) throws Exception {
        System.out.println("---------------------调用消费端------------------");
        return orderService.initOrder(id);
    }
}
