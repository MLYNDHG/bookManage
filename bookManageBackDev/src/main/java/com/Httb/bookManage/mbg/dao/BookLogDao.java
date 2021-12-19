package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.BookLog;

public interface BookLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookLog record);

    int insertSelective(BookLog record);

    BookLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookLog record);

    int updateByPrimaryKey(BookLog record);
}