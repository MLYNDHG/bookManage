package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Flowrule;

public interface FlowruleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Flowrule record);

    int insertSelective(Flowrule record);

    Flowrule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flowrule record);

    int updateByPrimaryKey(Flowrule record);
}