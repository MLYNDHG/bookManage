package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Sourcelist;

public interface SourcelistDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Sourcelist record);

    int insertSelective(Sourcelist record);

    Sourcelist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sourcelist record);

    int updateByPrimaryKey(Sourcelist record);
}