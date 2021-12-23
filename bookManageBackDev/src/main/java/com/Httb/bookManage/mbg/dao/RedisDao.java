package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Redis;

public interface RedisDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Redis record);

    int insertSelective(Redis record);

    Redis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Redis record);

    int updateByPrimaryKey(Redis record);
}