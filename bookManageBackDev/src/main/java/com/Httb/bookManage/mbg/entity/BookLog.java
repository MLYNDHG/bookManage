package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * book_log
 * @author 
 */
@ApiModel(value="com.Httb.finallytest.mbg.entity.BookLog")
@Data
public class BookLog implements Serializable {
    /**
     * 借阅id
     */
    @ApiModelProperty(value="借阅id")
    private Integer id;

    /**
     * 借阅的用户id
     */
    @ApiModelProperty(value="借阅的用户id")
    private Integer uid;

    /**
     * 借阅的图书id
     */
    @ApiModelProperty(value="借阅的图书id")
    private Integer bid;

    /**
     * 借阅时间
     */
    @ApiModelProperty(value="借阅时间")
    private Date timestart;

    /**
     * 还书时间
     */
    @ApiModelProperty(value="还书时间")
    private Date timeend;

    private static final long serialVersionUID = 1L;
}