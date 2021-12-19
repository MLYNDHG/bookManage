package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.UserLogin(user);
    }

    /**
     * 用户注册,修改用户信息
     */
    @PostMapping("/save")
    public Integer save(@RequestBody User user) {

        return userService.saveUser(user);
    }
}
