package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.BookExtDao;
import com.Httb.bookManage.mbg.entity.Book;
import com.Httb.bookManage.model.BookVO;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookExtDao bookExtDao;

    /**
     * 图书列表
     */
    public ResponsePageData<BookVO> selectBookList(RequestPageData<BookVO> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(), requestPageData.getPageCondition().getPageSize());
        List<BookVO> bookVOS = bookExtDao.selectBookInfoList(requestPageData.getCondition());
        // 时间格式化
        for (BookVO b :
            bookVOS) {
            b.setDate(b.getTime());
        }
        return new ResponsePageData<>(page);
    }

    /**
     * save 图书
     * 新增图书
     * 修改图书
     * status：1 软删除，0 存在。
     */
    public Integer saveBook(Book book) {
        if (book.getId() != null) {
            // 修改图书信息
            return bookExtDao.updateByPrimaryKeySelective(book);
        } else {
            // 新增图书
            // 添加图书时间
            book.setTime(new Date());
            return bookExtDao.insertSelective(book);
        }
    }

    /**
     * 删除图书
     */
    public Integer deleteBook(Integer id) {

        Book book = new Book();
        book.setId(id);
        book.setStatus(true);
        return bookExtDao.updateByPrimaryKeySelective(book);
    }

}
