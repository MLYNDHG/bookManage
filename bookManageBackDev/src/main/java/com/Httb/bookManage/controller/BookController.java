package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Book;
import com.Httb.bookManage.model.BookVO;
import com.Httb.bookManage.service.BookService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 图书列表
     */
    @PostMapping("/selectBookList")
    public ResponsePageData<BookVO> selectBookList(@RequestBody RequestPageData<BookVO> requestPageData) {

        return bookService.selectBookList(requestPageData);
    }

    /**
     * save 图书
     * 新增图书
     * 修改图书
     */
    @PostMapping("/saveBook")
    public Integer save(@RequestBody Book book) {

        return bookService.saveBook(book);
    }

    /**
     * 删除图书
     */
    @GetMapping("/deleteBook/{id}")
    public Integer delete(@PathVariable Integer id) {

        return bookService.deleteBook(id);
    }
}
