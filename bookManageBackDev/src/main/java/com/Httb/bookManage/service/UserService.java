package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.UserExtDao;
import com.Httb.bookManage.exception.NoDataFoundException;
import com.Httb.bookManage.exception.UserExistsException;
import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.util.CreateRandomCharData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserExtDao userExtDao;

    /**
     * 用户登录
     */
    public String UserLogin(User user) {
        // 通过用户名和密码查找用户
        User user1 = userExtDao.selectUserByUP(user);
        if (user1 == null) {
            throw new NoDataFoundException("用户名或密码错误!");
        }
        return CreateRandomCharData.createRandomCharData(30);
    }

    /**
     * 用户注册，修改用户信息
     * 分管理员和普通用户，密码已加密.
     * 需要传入 is_super: 1 管理员,0 普通用户.
     * status: 1 软删除, 0 存在.
     */
    public Integer saveUser(User user) {

        // 验证用户名是否重复
        existsUsername(user);

        if (user.getId() != null) {
            // 修改用户信息
            return userExtDao.updateByPrimaryKeySelective(user);
        } else {
            // 用户注册
            return userExtDao.insertUser(user);
        }
    }

    /**
     * 验证用户名是否重复
     */
    private void existsUsername(User user) {
        boolean tag = false;
        User user1 = userExtDao.selectUserByUsername(user.getUsername());
        if (user.getId() != null) {
            // 修改用户信息
            if (user1 != null && !user.getId().equals(user1.getId())) {
                // 如果查询的用户与修改用户id不同，则用户名重复
                tag = true;
            }
        } else if (user1 != null) {
            tag = true;
        }
        if (tag) {
            throw new UserExistsException("用户名重复！");
        }
    }
}
