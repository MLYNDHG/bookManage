package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.model.UserVO;
import com.Httb.bookManage.service.UserService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
//@RequestMapping("/user")
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

    /**
     * 用户头像上传
     */
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestBody MultipartFile file) throws IOException {

        return userService.uploadImage(file);
    }

    /**
     * 通过用户名修改密码
     */
    @PostMapping("/updatePassword")
    public Integer updatePassword(@RequestBody UserVO userVO) {

        return userService.updatePassword(userVO);
    }

    /**
     * 用户列表
     */
    @PostMapping("/selectUserList")
    public ResponsePageData<User> selectUserList(@RequestBody RequestPageData<User> requestPageData) {

        return userService.selectUserList(requestPageData);
    }
}
