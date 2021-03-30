package com.service;

import com.pojo.User;

/**
 * @author ping.dai
 */
public interface UserService {
    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public User getUser(int id) throws Exception;
}
