package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstitemsysteminfoDao {
    int deleteByPrimaryKey(Integer wid);

    int insert(Firstitemsysteminfo record);

    int insertSelective(Firstitemsysteminfo record);

    Firstitemsysteminfo selectByPrimaryKey(Integer wid);

    int updateByPrimaryKeySelective(Firstitemsysteminfo record);

    int updateByPrimaryKey(Firstitemsysteminfo record);
}