package com.cupid.demo.service.impl;

import com.cupid.demo.entity.UserEntity;
import com.cupid.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * User模块service
 * @author cupid
 * @date 2020-04-15
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 添加用户
     * @param user 用户信息
     * @return 执行结果
     */
    @Override
    public String addUser(UserEntity user) {
        return "一gi我里giao giao";
    }
}
