package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Primarytype;

public interface PrimarytypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Primarytype record);

    int insertSelective(Primarytype record);

    Primarytype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Primarytype record);

    int updateByPrimaryKey(Primarytype record);
}