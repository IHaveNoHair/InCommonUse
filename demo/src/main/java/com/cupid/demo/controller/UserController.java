package com.cupid.demo.controller;

import com.cupid.demo.common.entity.CodeMsg;
import com.cupid.demo.common.entity.ControllerResult;
import com.cupid.demo.entity.UserEntity;
import com.cupid.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * User接口
 * @author  cupid
 * @date  2020-04-10
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 添加用户信息
     * @param user 用户信息
     * @param bindingResult 返回默认错误信息
     * @return 返回执行结果
     */
    @RequestMapping("addUser")
    @ResponseBody
    public ControllerResult getUser(@RequestBody @Valid UserEntity user, BindingResult bindingResult){
        for (ObjectError allError : bindingResult.getAllErrors()) {
            logger.info("错误信息 : " + allError.getDefaultMessage());
            return ControllerResult.error(CodeMsg.BIND_ERROR);
        }
        return ControllerResult.success(userService.addUser(user));
    }
}