package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Firstitemsystem;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstitemsystemDao {
    int deleteByPrimaryKey(Integer mid);

    int insert(Firstitemsystem record);

    int insertSelective(Firstitemsystem record);

    Firstitemsystem selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Firstitemsystem record);

    int updateByPrimaryKey(Firstitemsystem record);
}