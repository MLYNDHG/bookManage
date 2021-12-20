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
}
