package com.cupid.demo.service;

import com.cupid.demo.entity.UserEntity;

/**
 * User接口服务
 * @author  cupid
 * @date  2020-04-10
 */
public interface UserService {
    /**
     * 添加用户
     * @param user 用户信息
     * @return 执行结果
     */
    String addUser(UserEntity user);
}
