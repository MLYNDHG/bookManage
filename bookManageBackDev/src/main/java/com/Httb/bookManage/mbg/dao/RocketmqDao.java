package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Rocketmq;

public interface RocketmqDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Rocketmq record);

    int insertSelective(Rocketmq record);

    Rocketmq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rocketmq record);

    int updateByPrimaryKey(Rocketmq record);
}