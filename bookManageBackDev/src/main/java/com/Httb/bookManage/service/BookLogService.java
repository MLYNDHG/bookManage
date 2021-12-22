package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.BookExtDao;
import com.Httb.bookManage.dao.BookLogExtDao;
import com.Httb.bookManage.mbg.entity.Book;
import com.Httb.bookManage.mbg.entity.BookLog;
import com.Httb.bookManage.model.BookLogVO;
import com.Httb.bookManage.model.BookVO;
import com.Httb.bookManage.util.DateUtil;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookLogService {

    // 图书
    @Resource
    private BookExtDao bookExtDao;

    // 借阅
    @Resource
    private BookLogExtDao bookLogExtDao;

    /**
     * 借阅列表
     */
    public ResponsePageData<BookLogVO> selectBookLogList(RequestPageData<BookLogVO> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(),requestPageData.getPageCondition().getPageSize());
        List<BookLogVO> bookLogVOS = bookLogExtDao.selectBookLogList(requestPageData.getCondition());
        for (BookLogVO b:
             bookLogVOS) {

            // 时间格式化
            b.setStartTime(b.getTimestart());
            if (b.getTimeend() != null) {
                b.setEndTime(b.getTimeend());
            }
        }
        return new ResponsePageData<>(page);
    }

    /**
     * save 借阅记录
     * 新增借阅 还书
     */
    public Integer saveBookLog(BookLogVO bookLogVO) {
        // 判断图书是否已借阅
        BookVO bookVO = new BookVO();
        bookVO.setId(bookLogVO.getBid());
        BookVO book = bookExtDao.selectBookInfoList(bookVO).get(0);
        if (book.getLog()) {
            // 图书已借阅，还书
            // 修改借阅记录的timeend
            bookLogVO.setTimeend(new Date());
            bookLogExtDao.updateBookLog(bookLogVO);
            // 修改图书的log为false
            book.setLog(false);
        } else {
            // 借阅图书
            // 添加借阅记录
            bookLogVO.setTimestart(new Date());
            bookLogExtDao.insertBookLog(bookLogVO);
            // 修改图书的log为true
            book.setLog(true);
        }
        return bookExtDao.updateBookInfo(book);
    }
}
