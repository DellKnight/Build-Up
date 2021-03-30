package com.service;

import com.pojo.User;

/**
 * @author ping.dai
 */
public interface OrderService {
    /**
     * 获取订单信息
     * @param id
     * @return
     */
    public User initOrder(int id) throws Exception;
}
