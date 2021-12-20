package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.UserExtDao;
import com.Httb.bookManage.exception.FileNotFoundException;
import com.Httb.bookManage.exception.NoDataFoundException;
import com.Httb.bookManage.exception.UserExistsException;
import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.model.UserVO;
import com.Httb.bookManage.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
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
        // 判断是否存在用户 或 用户是否已软删除
        if (user1 == null || user1.getStatus()) {
            throw new NoDataFoundException("用户名或密码错误!");
        }
//        return JWTUtils.getToken(user);
        return CreateRandomCharData.createRandomCharData(30);
    }

    /**
     * 用户注册，修改用户信息
     * 分管理员和普通用户，密码已加密.
     * 用户软删除，修改status.
     * 需要传入 is_super: 1 管理员,0 普通用户.
     * status: 1 软删除, 0 存在.
     */
    public Integer saveUser(User user) {

        // 验证用户名是否重复
        existsUsername(user);

        if (user.getId() != null) {
            // 修改用户信息
            return userExtDao.updateUser(user);
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

    /**
     * 用户头像上传
     */
    public String uploadImage(MultipartFile file) throws IOException {
        // 上传照片工具类
        String result = UploadImageUtil.uploadImage(file);
        if (result == null) {
            throw new FileNotFoundException("文件未提交！");
        }
        return result;
    }

    /**
     * 修改密码
     */
    public Integer updatePassword(UserVO userVO) {
        User user1 = userExtDao.selectUserByUP(userVO);
        if (user1 == null) {
            throw new NoDataFoundException("用户名或密码错误！");
        }
        return userExtDao.updatePassword(userVO);
    }

    /**
     * 用户列表
     */
    public ResponsePageData<User> selectUserList(RequestPageData<User> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(),requestPageData.getPageCondition().getPageSize());
        userExtDao.selectUser(requestPageData.getCondition());
        return new ResponsePageData<>(page);
    }

}
