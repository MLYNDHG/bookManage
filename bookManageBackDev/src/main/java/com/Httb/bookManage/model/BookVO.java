package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookVO extends Book {

    private Date timeFrom;

    private Date timeTo;

    /**
     * 解决返回的时间格式是时间戳的问题
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;
}
