package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.BookLog;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookLogVO extends BookLog {

    // 借阅时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    // 还书时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    // 图书名
    private String name;

    // 用户名
    private String username;
}
