package com.service;

/**
 * @author ping.dai
 */
public interface OrderService {
    /**
     * 模拟下单
     * @param userId
     * @param productId
     * @param sum
     */
    public void takeOrder(Long userId,Long productId,int sum);
}
