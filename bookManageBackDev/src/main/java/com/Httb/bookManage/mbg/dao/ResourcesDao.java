package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Resources;

public interface ResourcesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
}