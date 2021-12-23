package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Secondarytype;

public interface SecondarytypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Secondarytype record);

    int insertSelective(Secondarytype record);

    Secondarytype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Secondarytype record);

    int updateByPrimaryKey(Secondarytype record);
}