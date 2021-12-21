package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.BookDao;
import com.Httb.bookManage.mbg.entity.Book;
import com.Httb.bookManage.model.BookVO;

import java.util.List;

public interface BookExtDao extends BookDao {

    /**
     * 图书列表
     */
    List<BookVO> selectBookList(BookVO bookVO);

    /**
     * 图书查阅，借阅信息
     */
    List<BookVO> selectBookInfoList(BookVO bookVO);
}