package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.UserDao;
import com.Httb.bookManage.mbg.entity.User;

public interface UserExtDao extends UserDao {

    // 用户登录
    // 通过username,password查找用户
    User selectUserByUP(User user);

    // 用户查询
    User selectUser(User user);

    // 通过用户名查询用户
    User selectUserByUsername(String username);

    // 用户注册 用户插入
    Integer insertUser(User user);

}
