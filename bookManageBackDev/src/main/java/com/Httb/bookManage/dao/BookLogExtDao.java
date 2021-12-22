package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.BookLogDao;
import com.Httb.bookManage.model.BookLogVO;

import java.util.List;

public interface BookLogExtDao extends BookLogDao {

    /**
     * 借阅列表
     */
    List<BookLogVO> selectBookLogList(BookLogVO bookLogVO);
}
