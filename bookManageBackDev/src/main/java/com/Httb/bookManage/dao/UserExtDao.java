package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.UserDao;
import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.model.UserVO;

import java.util.List;

public interface UserExtDao extends UserDao {

    /**
     * 用户登录
     * 通过username,password查找用户
     */
    User selectUserByUP(User user);

    /**
     * 用户查询
     */
    List<User> selectUser(User user);

    /**
     * 通过用户名查询用户
     */
    User selectUserByUsername(String username);

    /**
     * 用户注册 用户插入
     */
    Integer insertUser(User user);

    /**
     * 修改用户信息
     */
    Integer updateUser(User user);

    /**
     * 修改用户密码
     */
    Integer updatePassword(UserVO userVO);
}
