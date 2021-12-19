package com.Httb.bookManage.mbg.dao;

import com.Httb.bookManage.mbg.entity.Book;

public interface BookDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}