package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.FlowSource;

public interface FlowSourceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowSource record);

    int insertSelective(FlowSource record);

    FlowSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowSource record);

    int updateByPrimaryKey(FlowSource record);
}