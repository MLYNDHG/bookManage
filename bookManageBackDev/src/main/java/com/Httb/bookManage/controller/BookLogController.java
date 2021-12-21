package com.Httb.bookManage.controller;

import com.Httb.bookManage.model.BookLogVO;
import com.Httb.bookManage.service.BookLogService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//@RequestMapping("/bookLog")
public class BookLogController {

    @Resource
    private BookLogService bookLogService;

    /**
     * 借阅列表
     */
    @PostMapping("/selectBookLogList")
    public ResponsePageData<BookLogVO> selectBookLogList(@RequestBody RequestPageData<BookLogVO> requestPageData) {

        return bookLogService.selectBookLogList(requestPageData);
    }

    /**
     * 借阅图书，还书
     */
    @PostMapping("/saveBookLog")
    public Integer saveBookLog(@RequestBody BookLogVO bookLogVO) {

        return bookLogService.saveBookLog(bookLogVO);
    }
}
